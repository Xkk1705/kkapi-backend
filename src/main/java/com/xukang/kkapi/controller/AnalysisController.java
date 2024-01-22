package com.xukang.kkapi.controller;

import com.xukang.kkapi.common.BaseResponse;
import com.xukang.kkapi.common.ErrorCode;
import com.xukang.kkapi.common.ResultUtils;
import com.xukang.kkapi.exception.BusinessException;
import com.xukang.kkapi.model.vo.UserInterfaceInfoVo;
import com.xukang.kkapi.service.UserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 统计分析接口controller
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;


    @GetMapping("/invoke")
    public BaseResponse<List<UserInterfaceInfoVo>> getPostVOById(@RequestParam("limit") Long limit) {
        if (limit <= 0 || limit >= 10) {// 最多top10
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<UserInterfaceInfoVo> invokeList = userInterfaceInfoService.getInvokeCount(limit);
        return ResultUtils.success(invokeList);
    }


}
