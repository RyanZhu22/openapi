package com.ryan.openapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ryan.openapi.common.model.entity.UserInterfaceInfo;

import java.util.List;


public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {


    List<UserInterfaceInfo> getTopInvokeInterfaceInfo(int limit);
}




