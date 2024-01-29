package com.xukang.kkapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xukang.kkapicommmon.entity.UserInterfaceInfo;

import java.util.List;

/**
 * @Entity com.xukang.kkapi.model.entity.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    /**
     * 获取前top3 接口使用次数和接口id
     * select interfaceInfoId, sum(user_interface_info.totalNum) as totolNum
     * from user_interface_info
     * group by user_interface_info.interfaceInfoId order by totalNum desc
     * limit 3
     *
     * @param limit
     * @return
     */
    List<UserInterfaceInfo> getInvokeCount(Long limit);

}




