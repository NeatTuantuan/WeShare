package com.edu.xd.controller.response;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName ResponseResult
 * @Description TODO
 * @Author tuantuan
 * @Date 2019/3/18 上午8:42
 * @Version 1.0
 * @Attention Copyright (C), 2004-2019, BDILab, XiDian University
 **/
@Data
@Component
public class ResponseResult {


    /**
     * 后端返回给前端的数据
     */
    private Object data;

    /**
     * 后端返回给前端的元数据，包括成功标识、状态码和消息
     */
    private MetaData meta;


    public ResponseResult(boolean success,String code,String message){

        this.meta=new MetaData(success,code,message);
    }
    public ResponseResult(){

    }

    public ResponseResult(boolean success,String code,String message,Object data){

        this(success, code, message);
        this.data=data;
    }

    public void setCode(String code){
        if(this.meta==null){
            this.meta = new MetaData();
        }
        this.meta.setCode(code);
    }

    public void setMessage(String message){
        if(this.meta == null){
            this.meta = new MetaData();
        }
        this.meta.setMessage(message);
    }

    public void setSuccess(Boolean success){
        if(this.meta == null){
            this.meta = new MetaData();
        }
        this.meta.setSuccess(success);
    }

    public void setData(Object data) {
        this.data = data;
    }
}
