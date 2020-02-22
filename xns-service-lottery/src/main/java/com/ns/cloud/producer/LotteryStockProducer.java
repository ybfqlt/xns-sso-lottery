package com.ns.cloud.producer;

import com.alibaba.fastjson.JSONObject;
import com.ns.cloud.mq.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author: xns
 * @Date: 20-2-21 下午8:13
 */
@Slf4j
@Component
public class LotteryStockProducer implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void send(JSONObject jsonObject){
        String jsonString = jsonObject.toString();
       log.info(">>jsonString: "+jsonString);
        String mesAgeId = UUID.randomUUID().toString().replace("-","");
        //封装消息
        Message mes = MessageBuilder.withBody(jsonString.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").setMessageId(mesAgeId).build();
        //构建回调返回的数据(消息id)
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(jsonString);
        rabbitTemplate.convertAndSend(RabbitmqConfig.MODIFY_EXCHANGE_NAME,RabbitmqConfig.BINDING_MODIFY_KEY,mes,correlationData);
    }

    //确认消息是否发送到Exchange
    //生产消息确认机制生产者往服务器发送消息的时候，采用应答机制
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        String jsonString =correlationData.getId();
        log.info("消息id: "+correlationData.getId());
        if(b){
            log.info(">>使用mq消息确认机制确保消息一定投递到mq中成功");
            return;
        }
        JSONObject jsonpObject = JSONObject.parseObject(jsonString);
        //生产者消息投递失败的话，采用递归重试机制
        send(jsonpObject);
        log.info(">>使用mq消息确认机制投递到mq中失败");
    }
}
