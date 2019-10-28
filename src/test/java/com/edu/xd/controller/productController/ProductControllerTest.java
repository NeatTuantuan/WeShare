package com.edu.xd.controller.productController;


import com.edu.xd.utils.JedisUtil;
import com.edu.xd.utils.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {


    @Autowired
    JedisUtil jedisUtil;

    @Autowired
    SerializeUtil serializeUtil;

    @Test
    public void redisConsumer() {

        String rawStr = jedisUtil.consumerMessage("testList");
        String[] strArry = rawStr.split(" ");



    }
}