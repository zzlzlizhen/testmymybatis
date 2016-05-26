package lz.mq.testDemo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.Basic;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
 
 
/**
 * 
 * 
 * 
 * @author lizhen
 * @since  
 */
public class QueueConsumer extends EndPoint implements Runnable,Consumer{
     
    public QueueConsumer(String endPointName) throws IOException{
        super(endPointName);       
    }
     
    public void run() {
        try {
        	//这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message。换句话说，在接收到该Consumer的ack前，他它不会将新的Message分发给它。
        	channel.basicQos(0, 1,false);
            channel.basicConsume(endPointName,false,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");    
    }
 
    /**
     * Called when new message is available.
     */
    public void handleDelivery(String consumerTag, Envelope env,
            BasicProperties props, byte[] body) throws IOException {
        Map map = (HashMap)SerializationUtils.deserialize(body);
        int i = (Integer)map.get("message number");
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date)+"---"+body.length+"Message Number1 "+ map.get("message number") + " received.");
        channel.basicAck(env.getDeliveryTag(),false);
    }
 
    public void handleCancel(String consumerTag) {
    }
    public void handleCancelOk(String consumerTag) {
    }
    public void handleRecoverOk(String consumerTag) {
    }
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
    }
}