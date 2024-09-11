import Taro from "@tarojs/taro";
import { View, Image } from "@tarojs/components";
import { AtButton } from "taro-ui";
import "./index.scss";
import headerBg from "../../assets/headerBg.jpg";
import GlobalFooter from "../../components/GlobalFooter";
import questionResult from "../../data/question_results.json";
import { getBestQuestionResult } from "../../utils/bizUtils";
import questions from "../../data/questions.json";

/**
 * 测试结果页面
 */
export default () => {
  // 获取答案
  const answerList = Taro.getStorageSync("answerList");
  if (!answerList || answerList.length < 1) {
    Taro.showToast({
      title: "答案为空",
      icon: "error",
      duration: 3000,
    });
  }
  // 获取最佳答案
  const result = getBestQuestionResult(answerList, questions, questionResult);

  return (
    <View className="resultPage">
      <View className="at-article__h1 title">{result.resultName}</View>
      <View className="at-article__h2 subTitle">{result.resultDesc}</View>
      <AtButton
        type="primary"
        circle
        className="enterBtn"
        onClick={() => {
          // 跳转到测试结果页面。reLaunch关闭所有页面，打开到应用内的某个页面，防止页面的无限叠加
          Taro.reLaunch({
            url: "/pages/index/index",
          });
        }}
      >
        再测一次
      </AtButton>
      <Image className="headerBg" src={headerBg} />
      <GlobalFooter />
    </View>
  );
};
