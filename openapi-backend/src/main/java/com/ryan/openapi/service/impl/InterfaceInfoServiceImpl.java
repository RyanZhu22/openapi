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
        // TODO 接下来自己写
        // 创建时，参数不能为空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

}




