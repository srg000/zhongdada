package com.srgstart.zhongdada.scoring;

import com.srgstart.zhongdada.common.ErrorCode;
import com.srgstart.zhongdada.exception.BusinessException;
import com.srgstart.zhongdada.model.entity.App;
import com.srgstart.zhongdada.model.entity.UserAnswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author srgstart
 * 评分策略执行器
 */
@Service
public class ScoringStrategyExecutor {

    /**
     * 策略列表：根据ScoringStrategy接口 从容器中找对应的实现类
     * 这两个实现类在使用 ScoringStrategyConfig注解时已经被注入到容器中了，因为ScoringStrategyConfig注解中包括了@Component
     */
    @Resource
    private List<ScoringStrategy> scoringStrategyList;


    /**
     * 评分
     *
     * @param choiceList
     * @param app
     * @return
     * @throws Exception
     */
    public UserAnswer doScore(List<String> choiceList, App app) throws Exception {
        Integer appType = app.getAppType();
        Integer appScoringStrategy = app.getScoringStrategy();
        if (appType == null || appScoringStrategy == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "应用配置有误，未找到匹配的策略");
        }
        // 根据注解获取策略
        for (ScoringStrategy strategy : scoringStrategyList) {
            if (strategy.getClass().isAnnotationPresent(ScoringStrategyConfig.class)) {
                ScoringStrategyConfig scoringStrategyConfig = strategy.getClass().getAnnotation(ScoringStrategyConfig.class);
                if (scoringStrategyConfig.appType() == appType && scoringStrategyConfig.scoringStrategy() == appScoringStrategy) {
                    return strategy.doScore(choiceList, app);
                }
            }
        }
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "应用配置有误，未找到匹配的策略");
    }
}