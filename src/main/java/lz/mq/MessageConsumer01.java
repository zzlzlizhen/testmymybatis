package lz.mq;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 消费消息
 * @author lizhen_pc
 *
 */
public class MessageConsumer01 implements MessageListener {
	
	private Logger logger = LoggerFactory.getLogger(MessageConsumer01.class);

	@Override
	public void onMessage(Message message) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			String str = new String(message.getBody(),"UTF-8");
			System.out.println("01Message Number "+sdf.format(date)+ str +" received.");
			Thread.sleep(1000);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}