package lz.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class Test01 {

	public static void main(String[] args) {
		JedisCluster jc = getCluster();
		testClusterString(jc);
	}

	public static Jedis getJedis(){
		Jedis jedis = new Jedis("192.168.132.200",6379);
		return jedis;
	}
	public static JedisCluster getCluster(){
		GenericObjectPoolConfig gopc = new GenericObjectPoolConfig();
		gopc.setMaxWaitMillis(-1);
		gopc.setMaxTotal(1000);
		gopc.setMinIdle(8);
		gopc.setMaxIdle(100);
		Set<HostAndPort> hap = new HashSet<HostAndPort>();
		hap.add(new HostAndPort("192.168.132.129",6400));
		//hap.add(new HostAndPort("192.168.132.129",6401));
		//hap.add(new HostAndPort("192.168.132.129",6402));
		//hap.add(new HostAndPort("192.168.132.129",6403));
		//hap.add(new HostAndPort("192.168.132.129",6404));
		//hap.add(new HostAndPort("192.168.132.129",6405));
		JedisCluster jc = new JedisCluster(hap,10000,20, gopc);
		return jc;
	}
	private static void testClusterString(JedisCluster jc){
		//System.out.println(jc.exists("name"));
		jc.set("key3","who am i");
		jc.set("key4","i am lizhen");
	}
	public static void testString(Jedis jedis){
		jedis.set("key3","who am i");
		jedis.set("key4","i am lizhen");
	}
	public static void testList(Jedis jedis){
		jedis.lpush("key1","zhangsan","lisi","wangwu","zhaoliu","maqi");
		List<String> list = jedis.lrange("key1",0,10);
		System.out.println(list);
	}
	public static void testGetAllKey(Jedis jedis){
		Set<String> set = jedis.keys("*");
		System.out.println(set);
	}
	public static void testDelKey(Jedis jedis,String key){
		jedis.del(key);
	}
}
