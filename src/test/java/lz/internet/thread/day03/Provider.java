/**
 * 
 */
package lz.internet.thread.day03;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lizhen_pc
 *123
 */
public class Provider implements Runnable{
	
	private BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>();
	private volatile boolean isRunning = true;
	private Random random = new Random();
	public Provider(BlockingQueue<Data> queue) {
		super();
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(isRunning){
			Data data = new Data();
			int randomInt = random.nextInt(500);
			data.setId(randomInt);
			data.setName("生产产品"+randomInt);
			try {
				Thread.sleep(randomInt);
				if(!queue.offer(data,1,TimeUnit.SECONDS)){
					System.out.println(randomInt+"---"+Thread.currentThread().getName()+"---生产数据失败：id="+data.getId()+"---name="+data.getName());
				}else{
					System.out.println(randomInt+"---"+Thread.currentThread().getName()+"---生产数据成功：id="+data.getId()+"---name="+data.getName());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		System.out.println("生产线程"+Thread.currentThread().getName()+"停止");
		isRunning = false;
	}
	
}
