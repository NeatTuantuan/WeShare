package com.edu.xd.controller;

import com.edu.xd.controller.response.ResponseResult;
import com.edu.xd.entity.Product;
import com.edu.xd.utils.RedisUtils;
import com.edu.xd.utils.SerializeUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/10/21 17:20
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@RestController
@Api(tags = {"产品相关功能"})
public class ProductController {
    @Autowired
    ResponseResult responseResult;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    SerializeUtil serializeUtil;

    @PostMapping(value = "/product/addProduct")
    public ResponseResult addProduct(@RequestBody Product product){
        redisUtils.set(product.getProductID(),serializeUtil.serialize(product));
        responseResult.setCode("200");
        responseResult.setMessage("添加产品信息成功");
        responseResult.setSuccess(true);
        return responseResult;
    }
}
