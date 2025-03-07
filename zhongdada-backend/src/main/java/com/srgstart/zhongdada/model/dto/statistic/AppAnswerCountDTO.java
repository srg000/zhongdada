package com.srgstart.zhongdada.model.dto.statistic;

import lombok.Data;

/**
 * 热门应用排行统计
 *
 * @author Administrator
 */
@Data
public class AppAnswerCountDTO {

    /**
     * appId
     */
    private Long appId;
    /**
     * 热门应用排行统计
     */
    private Long answerCount;
}
