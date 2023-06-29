package com.ryan.openapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.openapi.common.model.entity.InterfaceInfo;
import com.ryan.openapi.common.model.entity.User;
import com.ryan.openapi.common.model.entity.UserInterfaceInfo;

public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

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
