/**
 * 
 */
package lz.internet.thread.day04;

import java.util.concurrent.CountDownLatch;

/**
 * @author lizhen_pc
 *123
 */
public class UserCountDownLatch {

	final private static CountDownLatch countDown = new CountDownLatch(2);
	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月13日 上午10:33:24
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"线程开始");
				try {
					countDown.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"线程结束");
			}
			
		},"t1").start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"线程开始");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"线程结束");
				countDown.countDown();
			}
			
		},"t2").start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"线程开始");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"线程结束");
				countDown.countDown();
			}
			
		},"t3").start();
	}

}
