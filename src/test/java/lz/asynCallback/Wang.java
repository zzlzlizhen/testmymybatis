/**
 * 
 */
package lz.asynCallback;

/**
 * @author lizhen_pc
 *123
 */
public class Wang implements Callback {
	private Li li;
	public Wang(Li li){
		this.li=li;
	}
	public void askQuestion(final String question){
		new Thread(new Runnable(){
			/**
			 * 在Wang类里调用Li类里的方法executeMessage
			 */
			@Override
			public void run() {
				li.executeMessage(Wang.this,question);
			}
		}).start();
		play();
	}
	public void play(){
		System.out.println("我要打球去了");
	}
	
	@Override
	public void solve(String result) {
		System.out.println("小李告诉小王的答案："+result);
	}
}
