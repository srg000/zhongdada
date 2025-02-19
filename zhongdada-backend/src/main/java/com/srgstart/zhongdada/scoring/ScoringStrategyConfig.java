package com.srgstart.zhongdada.scoring;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author srgstart
 * 使用注解的形式，根据策略类使用不同注解配置 选择不同的策略实现
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ScoringStrategyConfig {

    /**
     * 应用类型
     *
     * @return
     */
    int appType();

    /**
     * 评分策略
     *
     * @return
     */
    int scoringStrategy();
}