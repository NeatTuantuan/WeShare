package com.edu.xd.controller.response;

import lombok.Data;

/**
 * @ClassName MetaData
 * @Description TODO
 * @Author tuantuan
 * @Date 2019/3/18 上午8:42
 * @Version 1.0
 * @Attention Copyright (C), 2004-2019, BDILab, XiDian University
 **/
@Data
public class MetaData {
    /**
     * 是否成功的标识
     */
    private Boolean success;

    /**
     * 返回码
     */
    private String code;


    /**
     * 返回消息
     */
    private String message;

    public MetaData(boolean success,String code,String message){
        this.success=success;
        this.code=code;
        this.message=message;
    }

    public MetaData() {

    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
