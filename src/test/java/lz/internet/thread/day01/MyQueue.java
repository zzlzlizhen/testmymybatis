/**
 * 
 */
package lz.internet.thread.day01;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程阻塞队列
 * 1,需要一个集合来存储数据，需要一个计数器来统计集合大小，需要二个变量来控制容器的上下限
 * 2,需要一个object对象，来做同步锁
 * 3,一个入队列的方法（如果队列已满，则等待阻塞），一个出队列的方法（如果队列已空，则等待阻塞）
 * 4,验证。需要二个线程，一个线程负责入队列，考虑队列满的情况。一个线程负责出队列
 * @author lizhen_pc
 *123
 */
public class MyQueue {

	//存放内容的容器
	private final LinkedList<Object> list = new LinkedList<Object>();
	//计数器 
	private final AtomicInteger count = new AtomicInteger();
	//容器的上线和下线
	private final int minSize=0;
	private final int maxSize;
	//lock对象
	Object lock = new Object();
	public MyQueue(int maxSize){
		this.maxSize=maxSize;
	}
	/**
	 * 向队列中加入数据。如果队列已满，则线程等待
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月9日 上午10:07:32
	 * @param obj
	 */
	public void put(Object obj){
		synchronized(lock){
			if(count.get()==this.maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.incrementAndGet();
			lock.notify();
			System.out.println("向队列中添加一个元素："+obj);
		}
	}
	/**
	 * 从队列中取数据，如果队列为空，则线程等待阻塞
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月9日 上午10:08:44
	 * @return
	 */
	public Object get(){
		Object ret = null;
		synchronized(lock){
			if(count.get()==this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ret = list.removeFirst();
			count.decrementAndGet();
			lock.notify();
			System.out.println("从队列中移除一个元素："+ret);
		}
		return ret;
	}
	
	
	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月9日 上午9:57:05
	 * @param args
	 */
	public static void main(String[] args) {
		final MyQueue mq = new MyQueue(5);
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mq.put("f");
				mq.put("g");
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					mq.get();
					Thread.sleep(1000);
					mq.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t2");
		t1.start();
		t2.start();
	}
}
