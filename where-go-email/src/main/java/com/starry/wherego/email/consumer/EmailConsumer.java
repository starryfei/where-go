package com.starry.wherego.email.consumer;

import com.starry.mq.EmailBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import com.starry.wherego.email.util.EmailUtil;

/**
 * ClassName: EmailConsumer
 * Description: email 监听并消费 消息队列中的数据
 *
 * @author: starryfei
 * @date: 2019-04-07 16:56
 **/
@Slf4j
@Service
@RocketMQMessageListener(topic = "email",consumerGroup="email-group")
public class EmailConsumer implements RocketMQListener<EmailBean> {

    @Override
    public void onMessage(EmailBean emailMq) {
        log.info(emailMq.toString());
        try {
            EmailUtil.sendEmail(emailMq.getCode(),emailMq.getEmailAddress());
        } catch (EmailException e) {
            e.printStackTrace();
        } finally {
            log.info("SEND SUCCESS");
        }
        log.info("______________________");

    }
}