package com.ns.cloud.dto;

import com.ns.cloud.constants.CodeConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xns
 * @Date: 20-1-28 下午10:07
 */
@Data
@NoArgsConstructor
@Accessors(chain=true)
public class Result<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    private int code;

    private String mes;

    private T data;

    public Result(int code, String mes,T data) {
        this.code = code;
        this.mes = mes;
        this.data = data;
    }


    public Result(int code,String mes){
        this.code=code;
        this.mes=mes;
        this.data = null;
    }

    public Result<T> no(String mes){
        return new Result<T>().setCode(500).setData(null).setMes(mes);
    }

    public Result<T> success(T data,String mes){
        return new Result<T>().setData(data).setCode(CodeConstants.CODE_SUCCESS).setMes(mes);
    }
}
