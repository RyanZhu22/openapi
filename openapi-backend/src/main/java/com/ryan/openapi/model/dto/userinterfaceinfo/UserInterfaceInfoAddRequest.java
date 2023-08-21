package com.ryan.openapi.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * create request
 *
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {


    /**
     * invoked user id
     */
    private Long userId;

    /**
     * interface id
     */
    private Long interfaceInfoId;

    /**
     * total of invoke
     */
    private Integer totalNum;

    /**
     * left number of invoke
     */
    private Integer leftNum;

}