package com.ns.sso.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xns
 * @Date: 20-1-28 下午10:07
 */
@Data
@Accessors(chain=true)
public class Result<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;

    public static final int FAIL_CODE = 500;

    private int code;

    private String mes;

    private T data;

    public Result(int code, String mes) {
        this.code = code;
        this.mes = mes;
    }

    public Result(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }
}
