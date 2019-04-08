package wheregoemail.demo.consumer;

import com.starry.mq.EmailBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import wheregoemail.demo.util.EmailUtil;

/**
 * ClassName: EmailConsumer
 * Description: TODO
 *
 * @author: starryfei
 * @date: 2019-04-07 16:56
 **/
@Slf4j
@Service
@RocketMQMessageListener(topic = "email",consumerGroup="email-group")
public class EmailConsumer implements RocketMQListener<EmailBean> {
    //    private static final Logger LOGGER = LoggerFactory.getLogger(EmailMQConsumer.class);
//    @Override
//    public void run(String... args) throws Exception {
//        LOGGER.info("[email consumer start]");
//    }

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