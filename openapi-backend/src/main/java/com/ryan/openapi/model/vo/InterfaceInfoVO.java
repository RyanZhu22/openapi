package com.ryan.openapi.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口信息封装视图（脱敏）
 *
 **/
@Data
public class InterfaceInfoVO implements Serializable {


    /**
     * 调用次数
     */
    private Integer totalNum;

    private String name;

    private static final long serialVersionUID = 1L;
}