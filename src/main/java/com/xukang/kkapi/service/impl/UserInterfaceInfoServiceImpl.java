package com.xukang.kkapi.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xukang.kkapi.common.ErrorCode;
import com.xukang.kkapi.exception.BusinessException;
import com.xukang.kkapi.exception.ThrowUtils;
import com.xukang.kkapi.model.entity.InterfaceInfo;
import com.xukang.kkapi.model.entity.UserInterfaceInfo;
import com.xukang.kkapi.service.UserInterfaceInfoService;
import com.xukang.kkapi.mapper.UserInterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long useId = userInterfaceInfo.getUseId();
        long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(useId <= 0 || interfaceInfoId <= 0, ErrorCode.PARAMS_ERROR);
        }
    }

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
        UserInterfaceInfo oldUserInterfaceInfo = this.getById(id);
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
            return this.updateById(oldUserInterfaceInfo);
        }
    }
}




