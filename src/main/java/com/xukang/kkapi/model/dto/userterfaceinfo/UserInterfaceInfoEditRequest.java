package com.xukang.kkapi.model.dto.userterfaceinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 编辑请求
 */
@Data
public class UserInterfaceInfoEditRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

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