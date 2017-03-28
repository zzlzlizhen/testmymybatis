/**
 * 
 */
package lz.internet.thread.day01;

/**
 * @author lizhen_pc
 *123
 */
public class DirtyRead {

	private String username = "username";
	private String password = "password";
	public synchronized void setValue(String username,String password){
		this.username = username;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.password = password;
		System.out.println("setValue:  username="+username+"   password="+password);
	}
	public synchronized void getValue(){
		System.out.println("getValue:  username="+username+"   password="+password);
	}
	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月8日 上午11:05:03
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final DirtyRead dr = new DirtyRead();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				dr.setValue("admin","123456");
			}
		});
		t1.start();
		Thread.sleep(2000);
		dr.getValue();
	}

}
