package com.edu.xd.controller.productController;


import com.edu.xd.controller.response.ResponseResult;
import com.edu.xd.entity.Product;
import com.edu.xd.utils.JedisUtil;
import com.edu.xd.utils.RedisUtils;
import com.edu.xd.utils.SerializeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    JedisUtil jedisUtil;


    /**
     * 添加产品
     */
    @ApiOperation(value = "添加产品")
    @PostMapping(value = "/product/addProduct")
//    @ApiImplicitParam(name = "product", value = "产品实体类")
    public ResponseResult addProduct(@RequestBody Product product){
        boolean addSuccess = redisUtils.set(product.getProductID(),serializeUtil.serialize(product));
        if (addSuccess) {
            responseResult.setCode(ProductCode.PRODUCT_ADD_SUCCESS.getCode());
            responseResult.setMessage("添加产品信息成功");
            responseResult.setSuccess(true);
            return responseResult;
        }else {
            responseResult.setCode(ProductCode.PRODUCT_ADD_FAIL.getCode());
            responseResult.setMessage("添加产品信息失败");
            responseResult.setSuccess(false);
            return responseResult;
        }
    }

    /**
     *根据产品id删除产品
     */
    @PostMapping(value = "/product/deleteProduct")
    @ApiImplicitParam(name = "productID", value = "产品ID")
    @ApiOperation(value = "添加删除商品")
    public ResponseResult deleteProduct(@RequestParam String productID){

        byte[] productBytes = redisUtils.get(productID);
        Product product = (Product) serializeUtil.unserialize(productBytes);

        if (product == null){
            responseResult.setCode(ProductCode.PRODUCT_DELETE_FAIL.getCode());
            responseResult.setMessage("该产品不存在");
            responseResult.setSuccess(false);
            return responseResult;
        }else {
            redisUtils.delete(productID);
            responseResult.setCode(ProductCode.PRODUCT_DELETE_SUCCESS.getCode());
            responseResult.setMessage("删除产品成功");
            responseResult.setSuccess(true);
            return responseResult;
        }

    }

    /**
     * 修改产品信息
     */
    @RequestMapping(value = "/product/modifyProduct",method = {RequestMethod.POST})
//    @ApiImplicitParam(name = "product", value = "产品实体")
    @ApiOperation(value = "修改产品信息")
    public ResponseResult modifyProduct(@RequestBody Product product){
        boolean modifySuccess = redisUtils.set(product.getProductID(),serializeUtil.serialize(product));
        if (modifySuccess) {
            responseResult.setCode(ProductCode.PRODUCT_MODIFY_SUCCESS.getCode());
            responseResult.setMessage("添加产品信息成功");
            responseResult.setSuccess(true);
            return responseResult;
        }else {
            responseResult.setCode(ProductCode.PRODUCT_MODIFY_FAIL.getCode());
            responseResult.setMessage("添加产品信息成功");
            responseResult.setSuccess(false);
            return responseResult;
        }

    }

    /**
     * 查看某一产品
     */
    @RequestMapping(value = "/product/getProductInfo",method = {RequestMethod.GET})
    @ApiImplicitParam(name = "productID", value = "产品ID")
    @ApiOperation(value = "获取产品信息（单个产品）")
    public ResponseResult getProductInfo(@RequestParam String productID){
        byte[] bytes = redisUtils.get(productID);
        if (bytes == null){
            responseResult.setCode(ProductCode.PRODUCT_GETPRODUCTINFO_FAIL.getCode());
            responseResult.setMessage("查询产品信息失败");
            responseResult.setSuccess(false);
        }else {
            Product product = (Product) serializeUtil.unserialize(bytes);
            responseResult.setCode(ProductCode.PRODUCT_GETPRODUCTINFO_SUCCESS.getCode());
            responseResult.setMessage("查询产品信息成功");
            responseResult.setSuccess(true);
            responseResult.setData(product);
        }
        return responseResult;

    }

    /**
     * 查看所有信息
     */
    @RequestMapping(value = "/product/getAllProductInfo",method = {RequestMethod.GET})
    @ApiOperation(value = "获取产品信息（所有产品）")
    public ResponseResult getAllProductsInfo(){

        try{
            //创建value集合
            List<Product> values = new ArrayList<>();

            //拿到所有值
            LinkedHashMap<String ,byte[]> map = redisUtils.getAll();
            //遍历map
            for (String key:map.keySet()){
                Product product = (Product)serializeUtil.unserialize(map.get(key)) ;
                values.add(product);
            }
            //为了前端美观，按照时间排序
            Collections.sort(values, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    Date d1 = o1.getCreateTime();
                    Date d2 = o2.getCreateTime();
                    if (d1 == null && d2 == null)
                        return 0;
                    if (d1 == null)
                        return -1;
                    if (d2 == null)
                        return 1;
                    return d1.compareTo(d2);
                }
            });

            responseResult.setCode(ProductCode.PRODUCT_GETALLPRODUCT_SUCCESS.getCode());
            responseResult.setMessage("查询所有产品成功");
            responseResult.setSuccess(true);
            responseResult.setData(values);
        }catch(Exception e){
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
        }


        return responseResult;

    }

    /**
     * 根据关键字查询产品
     */
    @RequestMapping(value = "/product/getProductInfoByKeyword",method = {RequestMethod.GET})
    @ApiImplicitParam(name = "keyWord", value = "关键字")
    @ApiOperation(value = "根据关键字查询产品")
    public ResponseResult getProductInfoByKeyWord(@RequestParam String keyWord){
        //拿到所有产品
        HashMap<String,byte[]> map = redisUtils.getAll();
        //创建value集合
        List<Product> values = new LinkedList<Product>();

        //遍历所有产品，匹配keyword
        for (String key:map.keySet()){
            Product product = (Product)serializeUtil.unserialize(map.get(key)) ;
            String name = product.getProduceName();
            if (name.contains(keyWord)){
                values.add(product);
            }
        }

        if(!values.isEmpty()){
            responseResult.setCode(ProductCode.PRODUCT_GETPRODUCTBYKEYWORD_SUCCESS.getCode());
            responseResult.setMessage("关键字查询产品信息成功");
            responseResult.setSuccess(true);
            responseResult.setData(values);
            return responseResult;
        }else {
            responseResult.setCode(ProductCode.PRODUCT_GETPRODUCTBYKEYWORD_FAIL.getCode());
            responseResult.setMessage("关键字查询产品信息失败");
            responseResult.setSuccess(false);
            return responseResult;
        }


    }

    @ApiOperation(value = "redis消息队列消费者消费消息")
    @GetMapping(value = "/product/redisConsumer")
    public ResponseResult redisConsumer(){
        String message[] = new String[3];
        String temp = jedisUtil.consumerMessage("testList");
        message = temp.split(" ");


        responseResult.setSuccess(true);
        responseResult.setMessage(ProductCode.PRODUCT_GETREDISMESSAGEQUEUE_SUCCESS.getMessage());
        responseResult.setCode(ProductCode.PRODUCT_GETREDISMESSAGEQUEUE_SUCCESS.getCode());
        responseResult.setData(message);
        return responseResult;
    }

//        List<String> list = Arrays.asList(new String[]{"猿医生", "CD", "yys"});
//
//
//    @RequestMapping("/product/sendMessage")
//    public String sendMessage(){
//
//        for (String message : list){
//            redisUtils.lpush("testList",serializeUtil.serialize(message));
//        }
//        return "success";
//    }


}
