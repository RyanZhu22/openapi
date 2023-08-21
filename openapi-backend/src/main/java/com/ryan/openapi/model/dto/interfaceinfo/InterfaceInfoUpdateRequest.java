package com.ryan.openapi.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * update request
 *
 */
@Data
public class InterfaceInfoUpdateRequest implements Serializable {

    /**
     * primary key
     */
    private Long id;

    /**
     * name
     */
    private String name;

    /**
     * description
     */
    private String description;

    /**
     * interface path
     */
    private String url;

    /**
     * request parameters
     */
    private String requestParams;

    /**
     * request header
     */
    private String requestHeader;

    /**
     * request header
     */
    private String responseHeader;

    /**
     * interface status（0-关闭，1-开启）
     */
    private Integer status;

    /**
     * request type
     */
    private String method;

    private static final long serialVersionUID = 1L;
}