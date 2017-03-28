/**
 * 
 */
package lz.internet.thread.day02;

import java.util.Random;

/**
 * @author lizhen_pc
 *123
 */
public class Main {

	/**
	 * 描述：
	 * 作者：李震
	 * 时间：2017年2月9日 下午5:09:31
	 * @param args
	 */
	public static void main(String[] args) {
		Master master = new Master(new Worker(),Runtime.getRuntime().availableProcessors());
		Random r = new Random();
		for(int i=0;i<1000;i++){
			Task t = new Task();
			t.setId(i);
			t.setName("任务"+i);
			t.setPrice(10);
			master.submit(t);
		}
		master.execute();
		long start = System.currentTimeMillis();
		while(true){
			if(master.isComplete()){
				long result = master.getResult();
				System.out.println("最终计算的结果："+result);
				long end = System.currentTimeMillis();
				System.out.println("1000个任务完成！共耗时："+(end-start));
				break;
			}
		}
	}

}
