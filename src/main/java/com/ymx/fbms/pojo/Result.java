package com.ymx.fbms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class Result {

    public static String CODE_200 = "200";
    public static String CODE_400 = "400";
    public static String CODE_500 = "500";

    private String code;
    private String msg;
    private Object data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code) {
        this.code = code;
    }

    public Result(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result success(String code) {
        return new Result(code);
    }

    public static Result success(String code, Object data) {
        return new Result(CODE_200, data);
    }


    public static Result error(String code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(String code) {
        return new Result(code);
    }
}
