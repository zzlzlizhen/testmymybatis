/**
 * 
 */
package lz.asynCallback;

/**
 * @author lizhen_pc
 *123
 */
public class Test {

	/**
	 * 异步回调测试
	 * 1，创建一个Callback的接口，声明一个回调方法slove，并且Wang类继承该接口
	 * 1，Wang的类里去异步调用Li类里的方法executeMessage(Callback callback，String question)，询问结果，无需等待结果返回，该干啥干啥
	 * 2，Li类收到这个请求之后，在忙着自己的事没有立即处理，等忙完后调用CallBack的回调方法slove(String result)，返回给调用者
	 * 3，Wang的类有slove方法的复写，得到返回结果，并进行相应的处理
	 * 描述：
	 * 作者：李震
	 * 时间：2016年7月27日 下午6:23:06
	 * @param args
	 */
	public static void main(String[] args) {
		Li li = new Li();
		Wang wang = new Wang(li);
		wang.askQuestion("1+1是多少？");
	}
}
