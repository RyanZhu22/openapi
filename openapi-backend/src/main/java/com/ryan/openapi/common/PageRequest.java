package com.ryan.openapi.common;

import com.ryan.openapi.constant.CommonConstant;
import lombok.Data;

/**
 * Common page request
 *
 */
@Data
public class PageRequest {

    /**
     * current page number
     */
    private long current = 1;

    /**
     * page size
     */
    private long pageSize = 10;

    /**
     * sort  field
     */
    private String sortField;

    /**
     * sort order (default ascending)
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
