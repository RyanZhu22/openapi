package com.ryan.openapi.common.service;


/**
 *
 */
public interface InnerUserInterfaceInfoService {

    /**
     * Statistics call interface
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
