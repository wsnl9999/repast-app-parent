package com.qy105.aaa.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/10 17:13
 * @description：
 * @modified By：
 */
@Data
public class  ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;

}
