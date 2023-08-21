package com.ryan.openapi.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * update request
 *
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {

    /**
     * primary key
     */
    private Long id;

    /**
     * invoked user id
     */
    private Long userId;

    /**
     * 接口 id
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

    /**
     * 0-normal，1-ban
     */
    private Integer status;
}