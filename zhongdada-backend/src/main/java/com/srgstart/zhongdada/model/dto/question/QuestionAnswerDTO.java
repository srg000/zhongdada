package com.srgstart.zhongdada.model.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDTO {

    /**
     * 题目标题
     */
    private String title;

    /**
     * 用户回答
     */
    private String userAnswer;

}

