/**
 * 
 */
package lz.internet.thread.day01;

/**
 * @author lizhen_pc
 *123
 */
public class MultThread{

	private static int num = 0;
	/**
	 * 类级别的锁
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月8日 上午10:54:05
	 * @param tag
	 */
	public static synchronized void printNum(String tag){
		try {
			if("a".equals(tag)){
				num = 100;
				System.out.println("this tag is "+tag);
				Thread.sleep(1000);
			}else{
				num = 200;
				System.out.println("this tag is "+tag);
			}
			System.out.println("num="+num+"   tag="+tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月8日 上午10:41:52
	 * @param args
	 */
	public static void main(String[] args) {
		final MultThread mt1 = new MultThread();
		final MultThread mt2 = new MultThread();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				MultThread.printNum("a");
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				MultThread.printNum("b");
			}
		});
		t1.start();
		t2.start();
	}

}
