package com.edu.xd;


import com.edu.xd.entity.Product;
import com.edu.xd.utils.RedisUtils;
import com.edu.xd.utils.SerializeUtil;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SerializeUtil serializeUtil;


    @org.junit.Test
    public void getAll(){

        //创建value集合
        List<Product> values = new ArrayList<>();

        //拿到所有值
        LinkedHashMap<String ,byte[]> map = redisUtils.getAll();
        //遍历map
        for (String key:map.keySet()){
            Product product = (Product)serializeUtil.unserialize(map.get(key)) ;
            values.add(product);
        }
        System.out.println("排序前");
        for (Product product :values){
            System.out.println(product.getProduceName()+":"+product.getCreateTime());
        }

        //
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

        System.out.println("排序后");
        for (Product product :values){
            System.out.println(product.getProduceName()+":"+product.getCreateTime());
        }
    }





}
