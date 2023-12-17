package com.xukang.kkapi.model.dto.userterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {

    /**
     * 调用者id
     */
    private Long useId;

    /**
     * 被调用接口id
     */
    private Long interfaceInfoId;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 调用状态（0-正常调用，1-禁止调用）
     */
    private Integer status;


    private static final long serialVersionUID = 1L;
}