package com.ryan.openapi.common;

import lombok.Data;

import java.io.Serializable;

/**
 * id請求
 *
 * @author ryan
 */
@Data
public class IdRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}