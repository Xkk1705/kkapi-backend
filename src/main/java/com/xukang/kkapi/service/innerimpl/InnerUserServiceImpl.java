package com.xukang.kkapi.service.innerimpl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xukang.kkapi.common.ErrorCode;
import com.xukang.kkapi.exception.BusinessException;
import com.xukang.kkapi.mapper.UserMapper;
import com.xukang.kkapicommmon.entity.User;
import com.xukang.kkapicommmon.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;


/**
 * 远程调用用户信息服务实现类
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByACK(String accessKye) {
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(StringUtils.isNotBlank(accessKye), User::getAccessKye, accessKye);
        User user = userMapper.selectOne(userLambdaUpdateWrapper);
        return user;
    }
}
