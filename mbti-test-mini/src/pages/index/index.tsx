import Taro from "@tarojs/taro";
import { View, Image } from "@tarojs/components";
import { AtButton } from "taro-ui";
import "./index.scss";
import headerBg from "../../assets/headerBg.jpg";
import GlobalFooter from "../../components/GlobalFooter";

/**
 * 主页，使用函数式组件
 */
export default () => {
  return (
    <View className="indexPage">
      <View className="at-article__h1 title">MBTI 性格测试</View>
      <View className="at-article__h2 subTitle">
        只需2分钟，就能准确地描述出你的性格特点
      </View>
      <AtButton
        type="primary"
        circle
        className="enterBtn"
        onClick={() => {
          // 跳转到做题页面，打开新页面
          Taro.navigateTo({
            url: "/pages/doQuestion/index",
          });
        }}
      >
        开始测试
      </AtButton>
      <Image className="headerBg" src={headerBg} />
      <GlobalFooter />
    </View>
  );
};
