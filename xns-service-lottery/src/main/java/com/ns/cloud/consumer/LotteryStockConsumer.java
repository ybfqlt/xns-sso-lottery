package com.ns.cloud.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ns.cloud.entities.Prize;
import com.ns.cloud.entities.LotteryRecord;
import com.ns.cloud.mapper.PrizeMapper;
import com.ns.cloud.mapper.RecordMapper;
import com.ns.cloud.mq.config.RabbitmqConfig;
//注意import的正确
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author: xns
 * @Date: 20-2-21 下午9:11
 */
@Slf4j
@Component
public class LotteryStockConsumer {
    @Autowired
    private PrizeMapper prizeMapper;

    @Autowired
    private RecordMapper recordMapper;

    //Queue：队列，PTP模式下，特定生产者向特定queue发送消息，消费者订阅特定的queue完成指定消息的接收
    //
    //   Message：消息体，根据不同通信协议定义的固定格式进行编码的数据包，来封装业务数据，实现消息的传输
    @RabbitListener(queues = RabbitmqConfig.MODIFY_STOCK_QUEUE)
    @RabbitHandler
    @Transactional
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        String msgId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "UTF-8");
        log.info(">>msg:{},msg:{}", message, msg);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        //获取奖品id
        Long prizeId = jsonObject.getLong("prizeId");
        Prize prize = prizeMapper.findByPrizeId(prizeId);
        if (prize == null) {
            log.warn(">>id=" + prizeId + "奖品不存在");
            return;
        }
        int judge = prizeMapper.updatePrizeStorage(prizeId, prize.getVersion());
        if (judge <= 0) {
            log.info(">>prizeId:{}修改库存失败>judge={}", prizeId, judge);
            return;
        }

        LotteryRecord lotteryRecord = new LotteryRecord();
        String userId = jsonObject.getString("userId");
        lotteryRecord.setUserId(userId).setPrizeId(prizeId).setState(1).setCreateTime(LocalDateTime.now());
        int insert = recordMapper.insert(lotteryRecord);
        if (insert <= 0) {
            return;
        }
        log.info(">>prizeId:{}修改库存成功>judge={},中奖", prizeId, judge);
    }
}
