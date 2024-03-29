package com.ryan.openapi.common;

import java.io.Serializable;
import lombok.Data;

/**
 * Common delete request
 *
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}