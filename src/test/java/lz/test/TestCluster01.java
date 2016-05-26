package lz.test;
import redis.clients.jedis.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
* jedis 测试 cluster
*
* @author steven
*/
public class TestCluster01 
{
    private static JedisCluster cluster;

	public static void main( String[] args )
    {
        Set<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
        // 这里只需要列出集群中的一个节点
        // JedisCluster 会自己去 discovery 其他的集群节点
        clusterNodes.add(new HostAndPort("192.168.132.129", 6400));
        clusterNodes.add(new HostAndPort("192.168.132.129", 6401));
        clusterNodes.add(new HostAndPort("192.168.132.129", 6402));
        clusterNodes.add(new HostAndPort("192.168.132.129", 6403));
        clusterNodes.add(new HostAndPort("192.168.132.129", 6404));
        clusterNodes.add(new HostAndPort("192.168.132.129", 6405));
        cluster = new JedisCluster(clusterNodes);
        // set/get
        cluster.set("foo", "jedis test");
        String value = cluster.get("foo");
        System.out.println("foo = " + value);
        // get cluster nodes
        System.out.println("------- cluster nodes --------");
        Map<String, JedisPool> nodes = cluster.getClusterNodes();
        Iterator<Map.Entry<String, JedisPool>> iterNodes = nodes.entrySet().iterator();
        while (iterNodes.hasNext()) {
            Map.Entry<String, JedisPool> entry = iterNodes.next();
            Jedis jedis = entry.getValue().getResource();
            System.out.println("============");
            System.out.println(entry.getKey() + "\n" + jedis.info());
        }
        // pub/sub
        System.out.println("------- pub/sub --------");
        // 这里随机取了两个分别用于 publish 和 subscribe 的 jedis 连接
        // Redis 不支持在同一个连接上既作为 publisher 又作为 subscriber
        final JedisPool jedisPool = nodes.entrySet().iterator().next().getValue();
        // 使用一个独立的线程来 publish
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        newFixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return ;
                }
                jedisPool.getResource().publish("channel-test", "hello, redis cluster!");
            }
        });
        // subscribe - 此处会一直阻塞来接收 publish 消息
        jedisPool.getResource().subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("onMessage - " + channel + ":" + message);
            }
            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println("onPMessage - " + pattern + "|" + channel + ":" + message);
            }
            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("onSubscribe - " + channel + ":" + subscribedChannels);
            }
            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println("onUnsubscribe - " + channel + ":" + subscribedChannels);
            }
            @Override
            public void onPUnsubscribe(String pattern, int subscribedChannels) {
                System.out.println("onPUnsubscribe - " + pattern + ":" + subscribedChannels);
            }
            @Override
            public void onPSubscribe(String pattern, int subscribedChannels) {
                System.out.println("onPSubscribe - " + pattern + ":" + subscribedChannels);
            }
        }, "channel-test");
    }
}