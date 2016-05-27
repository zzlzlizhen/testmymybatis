package lz.junittest;

import java.io.IOException;
import java.util.HashMap;

import lz.mq.testDemo.Producer;
import lz.mq.testDemo.QueueConsumer;
import lz.mq.testDemo.QueueConsumer2;

import org.junit.Test;

public class RabbitmqTest {

	
	@Test
	public void test() throws IOException, InterruptedException{
		QueueConsumer consumer = new QueueConsumer("queue");
		QueueConsumer2 consumer2 = new QueueConsumer2("queue");
        Thread consumerThread = new Thread(consumer);
        Thread consumerThread2 = new Thread(consumer2);
        consumerThread.start();
        consumerThread2.start();
         
        Producer producer = new Producer("queue");
         
        for (int i = 0; i < 10; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ooo = new ObjectOutputStream(baos);
            ooo.writeObject(message);
            byte[] bys = baos.toByteArray();*/
            System.out.println("Message Number "+ i +" sent.");
        }
        Thread.sleep(100000);
	}
	
}
