import Taro from "@tarojs/taro";
import { useEffect, useState } from "react";
import { AtButton, AtRadio } from "taro-ui";
import { View } from "@tarojs/components";
import "./index.scss";
import GlobalFooter from "../../components/GlobalFooter";
import questions from "../../data/questions.json";

/**
 * 主页，使用函数式组件
 */
export default () => {
  // 当前题目序号（从 1 开始）
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState<number>(1);
  // 当前题目
  const [currentQuestion, setCurrentQuestion] = useState(questions[0]);
  const radioOptions = currentQuestion.options.map((option) => {
    return {
      label: `${option.key}. ${option.value}`,
      value: option.key,
    };
  });
  // 当前回答
  const [currentAnswer, setCurrentAnswer] = useState<string>();
  // 回答列表
  const [answerList] = useState<string[]>([]);

  // 序号变化时，切换当前题目和当前回答
  useEffect(() => {
    setCurrentQuestion(questions[currentQuestionIndex - 1]);
    setCurrentAnswer(answerList[currentQuestionIndex - 1]);
  }, [currentQuestionIndex]);

  return (
    <View className="doQuestionPage">
      <View className="at-article__h2 questionContext">
        {currentQuestionIndex}、{currentQuestion.title}：
      </View>
      <view className="options_wrapper">
        <AtRadio
          options={radioOptions}
          value={currentAnswer}
          onClick={(value) => {
            setCurrentAnswer(value);
            answerList[currentQuestionIndex - 1] = value;
          }}
        />
      </view>
      {currentQuestionIndex < questions.length && (
        <AtButton
          type="primary"
          circle
          className="controlBtn"
          disabled={!currentAnswer}
          onClick={() => {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
          }}
        >
          下一题
        </AtButton>
      )}
      {currentQuestionIndex >= questions.length && (
        <AtButton
          type="primary"
          circle
          className="controlBtn"
          disabled={!currentAnswer}
          onClick={() => {
            // 传递答案，全局共享
            Taro.setStorageSync("answerList", answerList);
            // 跳转到测试结果页面，打开新页面
            Taro.navigateTo({
              url: "/pages/result/index",
            });
          }}
        >
          查看结果
        </AtButton>
      )}
      {currentQuestionIndex > 1 && (
        <AtButton
          circle
          className="controlBtn"
          onClick={() => {
            setCurrentQuestionIndex(currentQuestionIndex - 1);
          }}
        >
          上一题
        </AtButton>
      )}
      <GlobalFooter />
    </View>
  );
};
