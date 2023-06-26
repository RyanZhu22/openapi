package com.ryan.openapi.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.openapi.common.model.entity.User;

/**
 * 用户服务
 *
 * @author ryan
 */
public interface InnerUserService extends IService<User> {


    /**
     * 数据库中查询是否分配给用户密钥（accessKey, secretKey)
     * @param accessKey
     * @param secretKey
     * @return
     */
    User getInvokeUser(String accessKey, String secretKey);
}
