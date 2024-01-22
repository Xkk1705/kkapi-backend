package com.xukang.kkapi.service.innerimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xukang.kkapi.common.ErrorCode;
import com.xukang.kkapi.exception.BusinessException;
import com.xukang.kkapi.service.UserInterfaceInfoService;
import com.xukang.kkapicommmon.entity.UserInterfaceInfo;
import com.xukang.kkapicommmon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 远程调用用户接口统计关系实现列
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Resource
    UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public Boolean invokeCount(UserInterfaceInfo userInterfaceInfo) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long useId = userInterfaceInfo.getUseId();
        long id = userInterfaceInfo.getId();
        long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        if (useId <= 0 || id <= 0 || interfaceInfoId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 校验是否存在
        UserInterfaceInfo oldUserInterfaceInfo = userInterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String lock = String.valueOf(id).intern();
        // 统计调用次数
        synchronized (lock) {
            Integer leftNum = oldUserInterfaceInfo.getLeftNum();
            Integer totalNum = oldUserInterfaceInfo.getTotalNum();
            oldUserInterfaceInfo.setTotalNum(totalNum + 1);
            oldUserInterfaceInfo.setLeftNum(leftNum - 1);
            return userInterfaceInfoService.updateById(oldUserInterfaceInfo);
        }
    }

    @Override
    public UserInterfaceInfo getUserInterfaceInfoByUserInterfaceId(Long userId, Long interfaceInfoId) {
        LambdaQueryWrapper<UserInterfaceInfo> userInterfaceInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInterfaceInfoLambdaQueryWrapper.eq(userId != null && userId != 0, UserInterfaceInfo::getUseId, userId);
        userInterfaceInfoLambdaQueryWrapper.eq(interfaceInfoId != null && interfaceInfoId != 0, UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getOne(userInterfaceInfoLambdaQueryWrapper);
        return userInterfaceInfo;
    }
}
