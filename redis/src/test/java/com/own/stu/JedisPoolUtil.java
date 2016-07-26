package com.own.stu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by CHANEL on 2016/7/20.
 */
public class JedisPoolUtil {
    private static String host = "142.168.1.108";
    private static volatile JedisPool jedisPool;
    private JedisPoolUtil(){

    }
    public static JedisPool getJedisPoolInstance(){
        if(jedisPool == null){
            synchronized (JedisPool.class){
                if(jedisPool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, host);
                }
            }
        }
        return jedisPool;
    }
}