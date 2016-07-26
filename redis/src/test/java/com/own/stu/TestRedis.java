package com.own.stu;

import com.sun.deploy.util.StringUtils;
import jodd.util.CollectionUtil;
import jodd.util.StringUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.io.*;
import java.util.*;

/**
 * Created by CHANEL on 2016/7/20.
 */
public class TestRedis {
    private String host = "192.168.0.222";///"192.169.1.105";
    private int port = 6379;
    private Jedis jedis = null;

    @Before
    public void getJedis(){
        jedis = new Jedis(host, port);
    }

    @Test
    public void testPing(){
        System.out.println(jedis.ping());
    }

    @Test
    public void testKey(){
        System.out.println(jedis.set("k3", "vv3"));
    }

    @Test
    public void testStrig(){
        System.out.println(jedis.strlen("k3"));
        System.out.println(jedis.append("k3", "lov"));
        List<String> list = jedis.mget("k1", "k2", "k3");
        for(String s : list)
            System.out.println(s);
    }

    @Test
    public void testList(){
        jedis.lpush("list01", "01", "02", "03");
        jedis.rpush("list01", "-02", "-03");
        List<String> list = jedis.lrange("list01", 0, -1);
        for(String str : list)
            System.out.println(str);

        System.out.println(jedis.lindex("list01", 0));


    }

    @Test
    public void testSet(){
        jedis.sadd("set01", "1", "1", "2", "3", "4", "5");
        System.out.println(jedis.sismember("set01", "4"));
        System.out.println("numbers : " + jedis.scard("set01"));
        System.out.println(" ==== numbers ======== ");
        for(String str: jedis.smembers("set01"))
            System.out.println(str);

        System.out.println(" ==== random numbers ======== ");
        for(String str : jedis.srandmember("set01", 3))
            System.out.println(str);

    }

    @Test
    public void testHash(){
        jedis.hset("user", "name", "MENG");
        jedis.hset("user", "age", "28");
        Map<String , String > map = new HashMap<String, String>();
        map.put("Address", "SD");
        map.put("phone", "liantong");
        jedis.hmset("user", map);

        for(Map.Entry<String, String> entry :jedis.hgetAll("user").entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue()) ;
        }
    }

    @Test
    public void testZset(){
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("v1", 40d);
        map.put("v2", 60d);
        map.put("v3", 80d);
        map.put("v4", 90d);
        jedis.zadd("zset01", map);

        for(String str : jedis.zrange("zset01", 0, -1))
            System.out.println(str);
    }

    @Test
    public void testTransaction(){
        Transaction transcation = jedis.multi();
        Response<String> response = transcation.get("k2");
        transcation.set("k2", "vv222");
        transcation.lpush("list02", "a");
        transcation.lpush("list02", "b");
        transcation.lpush("list02", "c");
        transcation.exec();

        System.out.println(response.get());
    }

    @Test
    public void testTransactionWithLock(){
        jedis.watch("balance");

        int amtToSubtract = 10;
        int balance = Integer.parseInt(jedis.get("balance"));
        if(amtToSubtract > balance){
            jedis.unwatch();
            System.out.println("modify ");
            return;
        }else{
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", amtToSubtract);
            transaction.incrBy("debt", amtToSubtract);

            transaction.exec();
            System.out.println(jedis.get("balance"));
            System.out.println(jedis.get("debt"));
        }
    }

    @Test
    public void testJedisPool(){
        JedisPool pool = JedisPoolUtil.getJedisPoolInstance();

        Jedis jedis = null;
        jedis = pool.getResource();

        jedis.set("k2", "==");
        jedis.close();
    }

    @Test
    public void testPlay() throws IOException {

        JedisPool pool = JedisPoolUtil.getJedisPoolInstance();

        Jedis jedis = null;
        jedis = pool.getResource();

        String key = "words";
        long times = 200; //5000000;
        /*String words = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        int i= 1;
        jedis.del(key);
        for(String word : words.split("")) {
            jedis.sadd(key, word );
        }*/

        File file = new File("K:/data.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        StringBuffer sb = new StringBuffer();
        for(long i=1;i<times;i++){
            sb.setLength(0);
            sb.append(TestRedis.toStringForList(jedis.srandmember(key, 8))).append(",").append(UUID.randomUUID());
            writer.write(sb.toString());
            writer.newLine();
        }

        writer.flush();
        writer.close();
        jedis.close();
//        System.out.println(UUID.randomUUID());

       /* Long length = jedis.scard(key);

        List<String > result = new ArrayList<String>();
        for(int i=0;i<length;i++){
            result.add(jedis.spop(key));
        }
        Collections.sort(result);
        System.out.println(result);*/

    }

    @Test
    public void testSortPlay() throws IOException {
        File file = new File("K:/data-2.txt");
        File fileOut = new File("K:/data_out.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str;

        ArrayList<String> list = new ArrayList<String>();
        while((str = reader.readLine()) != null ){
            list.add(str);
        }
        //transform list to array
        String [] strs = new String[list.size()];
        list.toArray(strs);

        // merge sort
        Sort.mergeSort(strs);
        System.out.println(strs);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
        for(String s : strs){
            writer.write(s);
            writer.newLine();
        }
        reader.close();
        writer.close();

    }
    private static String toStringForList(List<String> list){
        StringBuffer sb = new StringBuffer();
        for(String s : list)
            sb.append(s);
        return sb.toString();
    }
}
