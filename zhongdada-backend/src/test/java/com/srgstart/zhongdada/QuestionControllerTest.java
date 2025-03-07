package com.srgstart.zhongdada;

import com.srgstart.zhongdada.controller.QuestionController;
import com.srgstart.zhongdada.model.dto.question.AiGenerateQuestionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author srgstart
 * @create 2025/03/06 16:19
 * @description
 */
@SpringBootTest
public class QuestionControllerTest {

    @Resource
    private QuestionController questionController;

    @Test
    public void test() throws InterruptedException {
        AiGenerateQuestionRequest aiGenerateQuestionRequest = new AiGenerateQuestionRequest();
        aiGenerateQuestionRequest.setAppId(3L);
        aiGenerateQuestionRequest.setQuestionNumber(10);
        aiGenerateQuestionRequest.setOptionNumber(2);

        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest, false);
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest, false);
        questionController.aiGenerateQuestionSSETest(aiGenerateQuestionRequest, true);

        Thread.sleep(1000000L);
    }
}
