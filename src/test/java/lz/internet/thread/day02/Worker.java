/**
 * 
 */
package lz.internet.thread.day02;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lizhen_pc
 *123
 */
public class Worker implements Runnable{
	private ConcurrentLinkedQueue<Task> workerQueue = new ConcurrentLinkedQueue<Task>();
	private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String, Object>();

	@Override
	public void run() {
		while(true){
			//从队列中取出一个任务并且移除
			Task input = this.workerQueue.poll();
			if(input == null){
				break;
			}
			//真正的去做业务处理
			Object output = handle(input);
			//将处理完成的任务结果放入master的结果容器
			this.resultMap.put(input.getId()+"",output);
		}
	}

	public Object handle(Task input){
		Object output = null;
		try {
			//任务的处理耗时操作
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workerQueue) {
		this.workerQueue = workerQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
}
