package com.xukang.kkapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xukang.kkapi.model.entity.UserInterfaceInfo;

/**
 * 用户接口关系
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);


    /**
     * 统计用户接口调用次数
     * @param userInterfaceInfo
     */
    Boolean invokeCount(UserInterfaceInfo userInterfaceInfo);
}
