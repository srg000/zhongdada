package com.srgstart.zhongdada.controller;

import com.srgstart.zhongdada.common.BaseResponse;
import com.srgstart.zhongdada.common.ErrorCode;
import com.srgstart.zhongdada.common.ResultUtils;
import com.srgstart.zhongdada.exception.ThrowUtils;
import com.srgstart.zhongdada.mapper.UserAnswerMapper;
import com.srgstart.zhongdada.model.dto.statistic.AppAnswerCountDTO;
import com.srgstart.zhongdada.model.dto.statistic.AppAnswerResultCountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用统计
 * @author Administrator
 */
@RestController
@RequestMapping("/app/statistic")
@Slf4j
public class AppStatisticController {

    @Resource
    private UserAnswerMapper userAnswerMapper;

    @GetMapping("/answer_count")
    public BaseResponse<List<AppAnswerCountDTO>> getAppAnswerCount() {
        return ResultUtils.success(userAnswerMapper.doAppAnswerCount());
    }

    @GetMapping("/answer_result_count")
    public BaseResponse<List<AppAnswerResultCountDTO>> getAppAnswerResultCount(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userAnswerMapper.doAppAnswerResultCount(appId));
    }
}


