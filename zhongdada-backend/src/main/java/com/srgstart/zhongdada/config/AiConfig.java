package com.srgstart.zhongdada.config;

import com.zhipu.oapi.ClientV4;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author srgstart
 * @create 2025/02/09 15:43
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "ai") // 加入这个前缀之后，就可以直接在配置文件中取这个前缀后面的变量值
@Data
public class AiConfig {

    /**
     * apiKey，需要从开放平台获取
     */
    private String apiKey;

    @Bean
    public ClientV4 getClientV4() {
        return new ClientV4.Builder(apiKey).build();
    }
}
