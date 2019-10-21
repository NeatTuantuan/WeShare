//package com.edu.xd.controller;
//
//import com.edu.xd.controller.response.ResponseResult;
//import com.edu.xd.utils.RedisUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * @ClassName test
// * @Description TODO
// * @Auther tuantuan
// * @Date 2019/10/21 15:57
// * @Version 1.0
// * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
// **/
//@Controller
//public class test {
//    @Autowired
//    RedisUtils redisUtils;
//    @Autowired
//    ResponseResult responseResult;
//
//    @PostMapping(value = "product/addProduct")
//    public void test(@RequestParam String s){
//        System.out.println(s);
//        redisUtils.set("test",s);
//    }
//
//}
