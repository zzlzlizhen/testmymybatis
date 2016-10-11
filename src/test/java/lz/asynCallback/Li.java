/**
 * 
 */
package lz.asynCallback;

/**
 * @author lizhen_pc
 *123
 */
public class Li {

	public void executeMessage(Callback callback,String question){
		System.out.println("小王的问题"+question);
		try {
			System.out.println("李在吃饭，请稍等");
			//休眠10秒，表示处理其他的事去了
			Thread.sleep(10000);
			String result = "答案是2";
			callback.solve(result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
