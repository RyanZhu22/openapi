package com.ryan.openapi.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL tool
 *
 */
public class SqlUtils {

    /**
     *  check if the sort field is legal (prevent SQL injection)
     *
     * @param sortField
     * @return
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
