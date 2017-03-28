/**
 * 
 */
package lz.internet.thread.day03;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lizhen_pc
 *123
 */
public class Consumer implements Runnable{

	private BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>();

	private Random random = new Random();
	
	public Consumer(BlockingQueue<Data> queue) {
		super();
		this.queue = queue;
	}


	@Override
	public void run() {
		while(true){
			try {
				int randomInt = random.nextInt(2000);
				Thread.sleep(randomInt);
				Data data = queue.take();
				System.out.println(randomInt+"---"+Thread.currentThread().getName()+"---消费的产品id="+data.getId()+"---name="+data.getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
