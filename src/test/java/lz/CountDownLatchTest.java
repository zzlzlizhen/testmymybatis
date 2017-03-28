package lz;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

public class CountDownLatchTest {

    private static CountDownLatch count = new CountDownLatch(10);
    private static ExecutorService service = Executors.newFixedThreadPool(10);

    private static ConcurrentHashMap<String,Future<Object>> chh = new ConcurrentHashMap<String,Future<Object>>();
    public static void main(String args[]) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
        	/*service.execute(new Runnable() {
				@Override
				public void run() {
					int timer = new Random().nextInt(5);
                    try {
	                    TimeUnit.SECONDS.sleep(timer);
	                    System.out.printf("%s时完成磁盘的统计任务,耗费%d秒.\n", new Date().toString(), timer);
	                    // 任务完成之后,计数器减一
	                    count.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});*/
        	Callable<Object> o = new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					int timer = new Random().nextInt(5);
                    try {
	                    TimeUnit.SECONDS.sleep(timer);
	                    System.out.printf("%s时完成磁盘的统计任务,耗费%d秒.\n", new Date().toString(), timer);
	                    // 任务完成之后,计数器减一
	                    count.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    return timer;
				}
        	};
        	Future<Object> future = service.submit(o);
        	chh.put("future"+i,future);
        }
        // 主线程一直被阻塞,知道count的计数器被设置为0
        count.await();
        System.out.printf("%s时全部任务都完成,执行合并计算.\n", new Date().toString());
        service.shutdown();
        int total = 0;
        for (Entry<String,Future<Object>> e:chh.entrySet()) {
			int result;
			try {
				result = (Integer)(e.getValue().get());
				total = total+result;
				System.out.println("任务"+e.getKey()+"计算的结果值："+(Integer)(e.getValue().get()));
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			}
		}
        System.out.println("所有的任务共计计算的结果值："+total);
    }
}