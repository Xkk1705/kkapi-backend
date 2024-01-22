package com.xukang.kkapi.service;

import com.xukang.kkapicommmon.entity.UserInterfaceInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 用户接口服务测试
 */
@SpringBootTest
class UserInterfaceInfoServiceTest {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;


    @Test
    public void setUp() {
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        userInterfaceInfo.setId(1L);
        userInterfaceInfo.setUseId(9L);
        userInterfaceInfo.setInterfaceInfoId(9L);
        Boolean result = userInterfaceInfoService.invokeCount(userInterfaceInfo);
        Assertions.assertTrue(result);
    }



}
