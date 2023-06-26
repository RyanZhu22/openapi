package com.ryan.openapi.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * interface invoke request
 *
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 名称
     */
    private Long id;

    /**
     * 请求参数
     */
    private String userRequestParams;

    /**
     * 请求类型
     */
    private String method;

}