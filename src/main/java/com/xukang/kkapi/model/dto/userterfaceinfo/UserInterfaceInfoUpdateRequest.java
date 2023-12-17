package com.xukang.kkapi.model.dto.userterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 调用者id
     */
    private Long useId;

    /**
     * 被调用接口id
     */
    private Long interfaceInfoId;

    /**
     * 调用次数
     */
    private Integer totalNum;

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