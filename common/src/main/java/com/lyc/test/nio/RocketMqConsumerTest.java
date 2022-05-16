package com.lyc.test.nio;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author liyuchun
 * @version 1.0
 * @description: test
 * @date 2022/5/16 11:56
 */
@Component
@RocketMQMessageListener(consumerGroup = "group-test-stock", topic = "TestTopic")
public class RocketMqConsumerTest  implements RocketMQListener {

    @Override
    public void onMessage(Object message) {
        System.out.println("rocketMq 收到消息 ， 如下！");
        System.out.println("Received message : "+ message);
    }
}
