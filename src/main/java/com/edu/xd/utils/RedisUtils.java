package com.edu.xd.utils;

import com.edu.xd.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtils
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/10/14 9:58
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Component
public class RedisUtils {
    @Resource
    @Autowired
    private RedisTemplate<String, byte[]> redisTemplate;
//    @Autowired
//    JedisConnectionFactory connectionFactory ;


    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public byte[] get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, byte[] value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, byte[] value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取所有缓存
     */
    public LinkedHashMap<String,byte[]> getAll(){
        //获得所有key
        Set<String> keys = redisTemplate.keys("*");
        //key-value集合
        LinkedHashMap<String,byte[]> map = new LinkedHashMap<>();
        //循环
        for (String key:keys){
            //获得key对应的value
            byte[] value = redisTemplate.opsForValue().get(key);
            map.put(key,value);
        }
        return map;
    }


    /**
     * 取值 - <brpop：阻塞式>
     * @param key 键
     * @param timeout 超时时间
     * @param timeUnit 给定单元粒度的时间段
     *                 TimeUnit.DAYS          //天
     *                 TimeUnit.HOURS         //小时
     *                 TimeUnit.MINUTES       //分钟
     *                 TimeUnit.SECONDS       //秒
     *                 TimeUnit.MILLISECONDS  //毫秒
     * @return
     */
    @SuppressWarnings("resource")
    public byte[] brpop(String key, long timeout, TimeUnit timeUnit) {
//        connectionFactory.setDatabase(1);
//        redisTemplate.setConnectionFactory(connectionFactory);
        try {
            return redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取值 - <rpop：非阻塞式>
     * @param key 键
     * @return
     */
    @SuppressWarnings("resource")
    public byte[] rpop(String key) {
//        connectionFactory.setDatabase(1);
//        redisTemplate.setConnectionFactory(connectionFactory);

        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 存值
     * @param key 键
     * @param value 值
     * @return
     */
    @SuppressWarnings("resource")
    public boolean lpush(String key, byte[] value) {
//        connectionFactory.setDatabase(1);
//        redisTemplate.setConnectionFactory(connectionFactory);

                try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}
