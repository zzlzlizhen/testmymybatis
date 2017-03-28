/**
 * 
 */
package lz.internet.thread.day02;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lizhen_pc
 *123
 */
public class Master {

	//1,需要一个承载所有任务的ConcurrentLinkQueue容器
	private ConcurrentLinkedQueue<Task> workerQueue = new ConcurrentLinkedQueue<Task>();
	
	//2,需要一个承载所有的worker容器HashMap容器
	private HashMap<String,Thread> workers = new HashMap<String,Thread>();

	//3,需要一个承载所有worker执行结果集的ConcurrentHashMap容器
	private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String, Object>();

	//4,构造方法
	public Master(Worker worker,int workerCount){
		//每个worker对象都需要有master的引用，workerQueue用于任务的领取，resultMap用户任务的提交
		worker.setWorkerQueue(workerQueue);
		worker.setResultMap(resultMap);
		for(int i=0;i<workerCount;i++){
			//key是每一个worker的名字,value表示线程执行的对象
			workers.put("子节点"+Integer.toString(i),new Thread(worker));
		}
	}
	
	//5,提交方法
	public void submit(Task task){
		this.workerQueue.add(task);
	}
	
	//6,需要一个执行的方法,启动应用程序让所有的worker工作
	public void execute(){
		for(Map.Entry<String,Thread> me:workers.entrySet()){
			me.getValue().start();
		}
	}

	//7,判断线程是否执行完毕
	public boolean isComplete() {
		for(Map.Entry<String,Thread> me:workers.entrySet()){
			if(me.getValue().getState()!=Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}

	//返回结果集
	public long getResult() {
		long result = 0l;
		for(Map.Entry<String,Object> map:resultMap.entrySet()){
			int price = (Integer)map.getValue();
			result = result+price;
		}
		return result;
	}
	
	
}
