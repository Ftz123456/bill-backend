package com.zsc.edu.bill.framework.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author harry_yao
 */

@Slf4j
@EnableAsync
@Configuration
//@Profile("!test") // test 环境下不启动异步
public class AsyncConfiguration implements AsyncConfigurer {

//    @Bean
//    public AsyncTaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(20);
//        executor.setMaxPoolSize(20);
//        executor.setQueueCapacity(500);
//        executor.setThreadNamePrefix("Executor");
//        executor.initialize();
//        return executor;
//    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Executor-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
        return (Throwable throwable, Method method, Object... obj)->{
            log.info("Method name -{}， Exception message -{}", method.getName(),throwable);
        };
    }
}
