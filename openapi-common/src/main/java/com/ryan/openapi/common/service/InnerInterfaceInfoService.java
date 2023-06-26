package com.ryan.openapi.common.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.openapi.common.model.entity.InterfaceInfo;

/**
* @author ryan
*/
public interface InnerInterfaceInfoService extends IService<InterfaceInfo> {


    /**
     * 从数据库中查询模拟接口是否存在（请求路径，请求方法，请求参数）
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);
}
