package com.ryan.openapi.common.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.openapi.common.model.entity.UserInterfaceInfo;

/**
 * @author ryan
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
 * @createDate 2023-06-02 19:44:17
 */
public interface InnerUserInterfaceInfoService extends IService<UserInterfaceInfo> {

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
