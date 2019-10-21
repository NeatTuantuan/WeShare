package com.edu.xd.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName product
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/10/21 15:47
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
@Component
public class Product implements Serializable {
    /**
     * 产品ID（唯一标识一个产品）
     */
    private String productID;
    /**
     * 产品名称
     */
    private String produceName;
    /**
     * 产品型号
     */
    private String productType;
    /**
     * 产品厂商
     */
    private String productManufacturer;
    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 产品分类
     */
    private List<String> productClassification;


    public String getProductID() {
        return productID;
    }

    public String getProduceName() {
        return produceName;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public List<String> getProductClassification() {
        return productClassification;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setProductClassification(List<String> productClassification) {
        this.productClassification = productClassification;
    }


}
