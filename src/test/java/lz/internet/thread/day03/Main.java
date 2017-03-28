/**
 * 
 */
package lz.internet.thread.day03;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lizhen_pc
 *123
 */
public class Main {

	private static BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>(10);

	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月10日 下午1:54:11
	 * @param args
	 */
	public static void main(String[] args) {
		Provider p1 = new Provider(queue);
		Provider p2 = new Provider(queue);
		Provider p3 = new Provider(queue);
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p1.stop();
		p2.stop();
		p3.stop();
	}

}
