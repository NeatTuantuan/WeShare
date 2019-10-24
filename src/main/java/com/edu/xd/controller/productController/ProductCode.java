package com.edu.xd.controller.productController;

public enum ProductCode {
    PRODUCT_ADD_SUCCESS("1001","添加产品成功"),
    PRODUCT_DELETE_SUCCESS("1002","删除产品成功"),
    PRODUCT_ADD_FAIL("2001","添加产品失败"),
    PRODUCT_DELETE_FAIL("2002","删除产品失败"),
    PRODUCT_MODIFY_SUCCESS("1003","修改产品成功"),
    PRODUCT_MODIFY_FAIL("2003","修改产品失败"),
    PRODUCT_GETPRODUCTINFO_SUCCESS("1004","查询产品信息成功"),
    PRODUCT_GETPRODUCTINFO_FAIL("2004","查询产品信息失败"),
    PRODUCT_GETALLPRODUCT_SUCCESS("1005","查询所有产品成功"),
    PRODUCT_GETPRODUCTBYKEYWORD_SUCCESS("1006","关键字查询产品成功"),
    PRODUCT_GETPRODUCTBYKEYWORD_FAIL("2006","关键字查询产品失败");

    private String code;
    private String message;

    ProductCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public void setCode(String code){ this.code = code; }
    public String getCode(){ return this.code; };

    public void setMessage(String message){ this.message = message; }
    public String getMessage(){ return this.message; }
}
