package com.ryan.openapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.openapi.model.entity.InterfaceInfo;
import com.ryan.openapi.model.entity.User;
import com.ryan.openapi.model.entity.UserInterfaceInfo;

/**
 * @author ryan
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
 * @createDate 2023-06-02 19:44:17
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {


    /**
     * 数据库查询是否已分配给用户密钥（accessKey, secretKey, boolean)
     * @param accessKey
     * @param secretKey
     * @return
     */
    User getInvokeUser(String accessKey, String secretKey);

    /**
     * 数据库查询模拟接口是否存在（请求路径、请求方法、boolean）
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);

    /**
     * @param userInterfaceInfo
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);


    /**
     * Statistics call interface
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
