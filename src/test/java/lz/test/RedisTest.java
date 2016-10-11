package lz.test;

import java.net.URI;

import redis.clients.util.JedisURIHelper;

public class RedisTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new RedisClient().show(); 
    	//test();
    }

    public static void test(){
    	URI uri = URI.create("http://root:123456@192.168.132.200:6379");
    	System.out.println(uri.getUserInfo());
        if (JedisURIHelper.isValid(uri)) {
          System.out.println(uri.getHost()+" "+uri.getPort()+" "+JedisURIHelper.getPassword(uri)+" "+JedisURIHelper.getDBIndex(uri));;
        }
    }
}