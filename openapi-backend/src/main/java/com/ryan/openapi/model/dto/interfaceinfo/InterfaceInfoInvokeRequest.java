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
     * name
     */
    private Long id;

    /**
     * request parameters
     */
    private String userRequestParams;

    /**
     * request type
     */
    private String method;

}