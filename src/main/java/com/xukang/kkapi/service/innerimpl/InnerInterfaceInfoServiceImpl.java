package com.xukang.kkapi.service.innerimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xukang.kkapi.common.ErrorCode;
import com.xukang.kkapi.exception.BusinessException;
import com.xukang.kkapi.mapper.InterfaceInfoMapper;
import com.xukang.kkapicommmon.entity.InterfaceInfo;
import com.xukang.kkapicommmon.service.InnerInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;


/**
 * 远程调用接口信息服务实现类
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public InterfaceInfo getInterfaceInfoByUri(String apiUrl) {
        LambdaQueryWrapper<InterfaceInfo> interfaceInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        interfaceInfoLambdaQueryWrapper.eq(StringUtils.isNotBlank(apiUrl),InterfaceInfo::getUrl,apiUrl);
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectOne(interfaceInfoLambdaQueryWrapper);
        return interfaceInfo;
    }
}
