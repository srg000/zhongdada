package com.srgstart.zhongdada.config;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author srgstart
 * @create 2025/03/06 15:59
 * @description
 */
@Configuration
@Data
public class VipSchedulerConfig {

    @Bean
    public Scheduler vipScheduler() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread thread = new Thread(r, "VIPThreadPool-" + threadNumber.getAndIncrement());
                // 非守护线程
                thread.setDaemon(false);
                return thread;
            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10, threadFactory);
        return Schedulers.from(executorService);
    }
}
