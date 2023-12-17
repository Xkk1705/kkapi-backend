package com.xukang.kkapi.model.dto.userterfaceinfo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.xukang.kkapi.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {

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