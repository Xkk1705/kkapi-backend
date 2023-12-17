package com.xukang.kkapi.model.dto.interfaceinfo;


import lombok.Data;

import java.io.Serializable;

/**
 * 调用（测试）接口请求参数
 */
@Data
public class UserInvokeRequest implements Serializable {
    /**
     * 接口id
     */
    private Long id;
    /**
     * 请求参数
     */
    private String requestParams;

    private static final long serialVersionUID = 1L;

}
