package lz.mq;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * 生产消息
 * @author lizhen_pc
 *
 */
@Service("messageProducer")
public class MessageProducer {
	
	private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

	@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message){
	  logger.info("to send message:{}",message);
      System.out.println("Message Number "+ message +" sent.");
	  amqpTemplate.convertAndSend("queueTestKey",message);
	}
}