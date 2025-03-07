package com.srgstart.zhongdada.model.dto.statistic;

import lombok.Data;

/**
 * 应用回答分布统计 DTO
 *
 * @author Administrator
 */
@Data
public class AppAnswerResultCountDTO {
    /**
     * 结果名称
     */
    private String resultName;
    /**
     * 应用回答分布统计
     */
    private String resultCount;
}
