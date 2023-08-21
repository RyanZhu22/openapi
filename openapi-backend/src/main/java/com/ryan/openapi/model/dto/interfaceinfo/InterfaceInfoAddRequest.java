package com.ryan.openapi.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * create request
 *
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {

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
     * request type
     */
    private String method;

}