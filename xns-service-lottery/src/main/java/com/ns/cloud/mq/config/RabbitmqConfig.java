package com.ns.cloud.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @Author: xns
 * @Date: 20-2-21 下午7:49
 */
@Component
public class RabbitmqConfig {

    //添加修改库存队列
    public static final String MODIFY_STOCK_QUEUE="modify_stock_queue";

    //交换机名称
    public static final String MODIFY_EXCHANGE_NAME="modify_exchange_name";

    //绑定key,交换机绑定队列时需要指定
    public static final String BINDING_MODIFY_KEY="modifyRoutingKey";

    //添加库存队列
    @Bean
    public Queue directModifyStockQueue(){
        return new Queue(MODIFY_STOCK_QUEUE);
    }

    //定义交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MODIFY_EXCHANGE_NAME);
    }

    //rabbitMq 中的broker由exchange、binder queue三部分组成，其中exchange和binding组成了消息的路由键；客户端Producer通过连接channel和server进行通信，Consumer从queue获取消息进行消费，rabbit有消息确认机制
    //修改库存队列绑定交换机
    @Bean
    Binding bindingExchangeinteralDicQueue(){
        return BindingBuilder.bind(directModifyStockQueue()).to(directExchange()).with(BINDING_MODIFY_KEY);
    }
}
