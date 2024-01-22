package com.xukang.kkapi.model.vo;

import lombok.Data;

import com.xukang.kkapicommmon.entity.UserInterfaceInfo;

/**
 * 接口用户关系表vo
 * @TableName user_interface_info
 */

@Data
public class UserInterfaceInfoVo extends UserInterfaceInfo{
  String InterfaceInfoUrl;
}