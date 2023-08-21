package com.ryan.openapi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.openapi.common.ErrorCode;
import com.ryan.openapi.common.model.entity.InterfaceInfo;
import com.ryan.openapi.common.service.InnerInterfaceInfoService;
import com.ryan.openapi.exception.BusinessException;
import com.ryan.openapi.mapper.InterfaceInfoMapper;
import com.ryan.openapi.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // parameters cannot be null when creating
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        // validation if there are parameters
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "The name is too long");
        }
    }

}




