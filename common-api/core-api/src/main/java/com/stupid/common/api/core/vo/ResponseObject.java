package com.stupid.common.api.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {

    /**
     * 注意需要提供无参构造
     * 否则远程调用序列化会报错
     */

    private Integer code = 0;
    private String message = "success";
    private Boolean success = true;
    private T data;

    public ResponseObject(Boolean success) {
        this.success = success;
        if(!success){
            this.code = -1;
            this.message = "fail";
        }
    }

    public ResponseObject(Boolean success,T t) {
        this.success = success;
        if(!success){
            this.code = -1;
            this.message = "fail";
            this.data = t;
        }
    }

    public ResponseObject(T data) {
        this.data = data;
    }
}
