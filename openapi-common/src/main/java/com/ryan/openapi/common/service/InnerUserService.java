package com.ryan.openapi.common.service;

import com.ryan.openapi.common.model.entity.User;

/**
 * 用户服务
 *
 * @author ryan
 */
public interface InnerUserService {


    /**
     * 数据库中查询是否分配给用户密钥（accessKey)
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
