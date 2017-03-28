/**
 * 
 */
package lz.internet.thread.day01;

/**
 * @author lizhen_pc
 *123
 */
public class MyThread extends Thread{

	private int count = 5;
	
	/**
	 * 对象级别的锁
	 */
	@Override
	public synchronized void run(){
		count--;
		System.out.println(this.currentThread().getName()+":count="+count);
	}
	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月8日 上午10:28:41
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		Thread t1 = new Thread(thread,"t1");
		Thread t2 = new Thread(thread,"t2");
		Thread t3 = new Thread(thread,"t3");
		Thread t4 = new Thread(thread,"t4");
		Thread t5 = new Thread(thread,"t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

}
