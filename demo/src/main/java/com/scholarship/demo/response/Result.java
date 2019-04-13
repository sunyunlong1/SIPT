package com.scholarship.demo.response;

import lombok.Data;

/**
 * 统一返回json
 */
@Data
public class Result {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息提示
     */
    private String message;
    /**
     * 返回的数据
     */
    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(){

    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", date=" + data +
                '}';
    }
}
