package com.ryan.openapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.openapi.common.model.entity.InterfaceInfo;
import com.ryan.openapi.model.entity.PostFavour;

public interface InterfaceInfoService  extends IService<InterfaceInfo>  {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
