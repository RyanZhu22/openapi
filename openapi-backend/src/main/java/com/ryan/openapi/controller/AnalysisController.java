package com.ryan.openapi.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryan.openapi.annotation.AuthCheck;
import com.ryan.openapi.common.BaseResponse;
import com.ryan.openapi.common.ErrorCode;
import com.ryan.openapi.common.ResultUtils;
import com.ryan.openapi.common.model.entity.InterfaceInfo;
import com.ryan.openapi.common.model.entity.UserInterfaceInfo;
import com.ryan.openapi.exception.BusinessException;
import com.ryan.openapi.mapper.UserInterfaceInfoMapper;
import com.ryan.openapi.model.vo.InterfaceInfoVO;
import com.ryan.openapi.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @AuthCheck(mustRole = "admin")
    @GetMapping("/top/interface/invoke")
    public BaseResponse<List<InterfaceInfoVO>> getTopInterfaceInfo() {
        List<UserInterfaceInfo> topInvokeInterfaceInfo = userInterfaceInfoMapper.getTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = topInvokeInterfaceInfo.stream()
            .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));

        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtil.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }
}
