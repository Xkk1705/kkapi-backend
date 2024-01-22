package com.xukang.kkapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xukang.kkapi.annotation.AuthCheck;
import com.xukang.kkapi.common.ErrorCode;
import com.xukang.kkapi.exception.BusinessException;
import com.xukang.kkapi.exception.ThrowUtils;
import com.xukang.kkapi.mapper.InterfaceInfoMapper;
import com.xukang.kkapi.model.vo.UserInterfaceInfoVo;
import com.xukang.kkapi.service.UserInterfaceInfoService;
import com.xukang.kkapi.mapper.UserInterfaceInfoMapper;
import com.xukang.kkapicommmon.entity.InterfaceInfo;
import com.xukang.kkapicommmon.entity.UserInterfaceInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

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

    @Override
    @AuthCheck(mustRole = "admin")
    public List<UserInterfaceInfoVo> getInvokeCount(Long limit) {
        List<UserInterfaceInfo> totolNumAndInterfaceInfoIdList = userInterfaceInfoMapper.getInvokeCount(limit);
        if (totolNumAndInterfaceInfoIdList.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<UserInterfaceInfoVo> userInterfaceInfoVos = totolNumAndInterfaceInfoIdList.stream().map(item -> {
            InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(item.getInterfaceInfoId());
            UserInterfaceInfoVo userInterfaceInfoVo = new UserInterfaceInfoVo();
            BeanUtils.copyProperties(item, userInterfaceInfoVo);
            userInterfaceInfoVo.setInterfaceInfoUrl(interfaceInfo.getUrl());
            return userInterfaceInfoVo;
        }).collect(Collectors.toList());
        return userInterfaceInfoVos;
    }
}




