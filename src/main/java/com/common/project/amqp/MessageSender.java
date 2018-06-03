package com.common.project.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class MessageSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
    }
    /**
     * 测试无返回消息的
     */
    public void send() {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGES_NAME, RabbitMQConstant.ROUTING_KEY, ">>>>>> Hello World".getBytes(), correlationData);
        log.info(">>>>>>>>>> Already sent message");
    }

    /**
     * 测试有返回消息的，需要注意一些问题
     */
    public void sendAndReceive() {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Object o = rabbitTemplate.convertSendAndReceive(RabbitMQConstant.EXCHANGES_NAME, RabbitMQConstant.ROUTING_REPLY_KEY, ">>>>>>>> Hello World Second".getBytes(), correlationData);
        log.info(">>>>>>>>>>> {}", Objects.toString(o));
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            log.info(">>>>>>> 消息id:{} 发送成功", correlationData.getId());
        } else {
            log.info(">>>>>>> 消息id:{} 发送失败", correlationData.getId());
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("消息id：{} 发送失败", message.getMessageProperties().getCorrelationId());
    }
}
