package com.edu.xd.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RedisUtilsTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    void get(String id){
        id = "ssssss";
        byte[] bytes = redisUtils.get(id);
        System.out.println(bytes);
    }

//    @Test
//    void getAll() {
//        HashMap<String,byte[]> map = redisUtils.getAll();
//        System.out.println(map.keySet().isEmpty());
//        for (String key :map.keySet()){
//            System.out.println(key);
//        }
//    }
}