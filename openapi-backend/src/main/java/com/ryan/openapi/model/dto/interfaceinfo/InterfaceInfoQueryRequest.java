package com.ryan.openapi.model.dto.interfaceinfo;

import com.ryan.openapi.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * query request
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoQueryRequest extends PageRequest implements Serializable {

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
     * interface status（0-off，1-on）
     */
    private Integer status;

    /**
     * request type
     */
    private String method;

    /**
     * creator
     */
    private Long userId;

}