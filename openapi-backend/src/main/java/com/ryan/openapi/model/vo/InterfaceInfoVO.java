package com.ryan.openapi.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * interface information vo
 *
 **/
@Data
public class InterfaceInfoVO implements Serializable {


    /**
     * total of invoke
     */
    private Integer totalNum;

    private String name;

    private static final long serialVersionUID = 1L;
}