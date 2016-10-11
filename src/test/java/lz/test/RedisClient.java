package lz.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lz.model.User;
import lz.utils.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
public class RedisClient {
    private Jedis jedis;
    private JedisPool jedisPool;
    private ShardedJedis shardedJedis;
    private ShardedJedisPool shardedJedisPool;
    
    public RedisClient() 
    { 
        initialPool(); 
        //initialShardedPool(); 
        //shardedJedis = shardedJedisPool.getResource(); 
        jedis = jedisPool.getResource(); 
    } 
 
    public static void main(String args[]){
    	Map<String,Object> map = new HashMap<String,Object>();
    	RedisClient rc = new RedisClient();
    	/*User user = new User();
    	user.setId("1");
    	user.setName("张三");
    	user.setPhone("1232343445");
    	map.put(user.getId(),user);
    	rc.setString(map);*/
    	User user = (User)rc.getString("1");
    	System.out.println(user.getName());
    }
    public void setString(Map<String,Object> map){
    	for(Map.Entry<String,Object> entry : map.entrySet()){
    		jedis.set(entry.getKey().getBytes(),SerializeUtil.serialize(entry.getValue()));
    	}
    }
    public Object getString(String key){
    	return SerializeUtil.unserialize(jedis.get(key.getBytes()));
    }
    /**
     */
    private void initialPool() 
    { 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxTotal(20);
        config.setMaxIdle(5); 
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false); 
        //jedisPool = new JedisPool(config,"192.168.132.129",6379,1000,"");
        jedisPool = new JedisPool(config, "192.168.132.129",6379); 
    }
    
    /** 
     */ 
    private void initialShardedPool() 
    { 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxTotal(20);
        config.setMaxIdle(5); 
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false); 
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        //shards.add(new JedisShardInfo("192.168.132.132", 6379, "lizhen123456")); 
        shards.add(new JedisShardInfo("http://root:123456@192.168.132.200:6379"));
        shardedJedisPool = new ShardedJedisPool(config, shards); 
    } 

    public void show() {     
        KeyOperate(); 
        /*StringOperate(); 
        ListOperate(); 
        SetOperate();
        SortedSetOperate();
        HashOperate(); */
        jedisPool.returnResourceObject(jedis);
        //shardedJedisPool.returnResourceObject(shardedJedis);
    } 

    private void KeyOperate() 
    { 
        System.out.println("======================key=========================="); 
        System.out.println(jedis.flushDB());
        System.out.println(shardedJedis.exists("key999")); 
        System.out.println(shardedJedis.set("key001", "value001")); 
        System.out.println(shardedJedis.exists("key001"));
        System.out.println(shardedJedis.set("key002", "value002"));
        System.out.println();
        Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
        System.out.println(jedis.del("key002"));
        System.out.println(shardedJedis.exists("key002"));
        System.out.println(jedis.expire("key001", 5));
        try{ 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e){ 
        } 
        System.out.println(jedis.ttl("key001"));
        System.out.println(jedis.persist("key001"));
        System.out.println(jedis.ttl("key001"));
        System.out.println(jedis.type("key001"));
    }
    private void StringOperate() 
    {  
        System.out.println("======================String_1=========================="); 
        System.out.println(jedis.flushDB());
        
        jedis.set("key001","value001");
        jedis.set("key002","value002");
        jedis.set("key003","value003");
        System.out.println(jedis.get("key001"));
        System.out.println(jedis.get("key002"));
        System.out.println(jedis.get("key003"));
        
        
                
            
    }
    private void ListOperate() 
    { 
        System.out.println("======================list=========================="); 
        System.out.println(jedis.flushDB()); 

        System.out.println("=========================");
        shardedJedis.lpush("stringlists", "vector"); 
        shardedJedis.lpush("stringlists", "ArrayList"); 
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "LinkedList");
        shardedJedis.lpush("stringlists", "MapList");
        shardedJedis.lpush("stringlists", "SerialList");
        shardedJedis.lpush("stringlists", "HashList");
        shardedJedis.lpush("numberlists", "3");
        shardedJedis.lpush("numberlists", "1");
        shardedJedis.lpush("numberlists", "5");
        shardedJedis.lpush("numberlists", "2");
        System.out.println(shardedJedis.lrange("stringlists", 0, -1));
        System.out.println(shardedJedis.lrange("numberlists", 0, -1));
        
        System.out.println("=============ɾ=============");
        System.out.println(shardedJedis.lrem("stringlists", 2, "vector")); 
        System.out.println(shardedJedis.lrange("stringlists", 0, -1));
        System.out.println(shardedJedis.ltrim("stringlists", 0, 3));
        System.out.println(shardedJedis.lrange("stringlists", 0, -1));
        System.out.println(shardedJedis.lpop("stringlists")); 
        System.out.println(shardedJedis.lrange("stringlists", 0, -1));
        
        System.out.println("========================");
        shardedJedis.lset("stringlists", 0, "hello list!"); 
        System.out.println(shardedJedis.lrange("stringlists", 0, -1));
        System.out.println("=======================");
        System.out.println(shardedJedis.llen("stringlists"));
        System.out.println(shardedJedis.llen("numberlists"));
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        sortingParameters.limit(0, 3);
        System.out.println(shardedJedis.sort("stringlists",sortingParameters)); 
        System.out.println(shardedJedis.sort("numberlists"));
        System.out.println(shardedJedis.lrange("stringlists", 1, -1));
        System.out.println(shardedJedis.lindex("stringlists", 2)+"\n");
    }
    
}