package com.zx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncPoolConfig implements AsyncConfigurer {
    private static final Logger log = LoggerFactory.getLogger(AsyncPoolConfig.class);

    //把这个方法的返回对象 交给spring ioc管理
    @Bean//<bean id="getAsyncExecutor" class="">
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数:线程池刚创建的时候 就会初始化10个线程
        threadPoolTaskExecutor.setCorePoolSize(8);
        //非核心线程：15-10=5 核心线程不够用的时候
        threadPoolTaskExecutor.setMaxPoolSize(16);
        //缓冲队列的个数 :队列作为一个缓冲的工具，
        //当没有足够的线程去处理任务时，可以将任务放进队列中，以队列先进先出的特性来执行工作任务
        threadPoolTaskExecutor.setQueueCapacity(100);
        //非核心线程数 还回去的时间  如果空闲超过10秒就被回收
        threadPoolTaskExecutor.setKeepAliveSeconds(10);
        //设置线程的前缀名称
        threadPoolTaskExecutor.setThreadNamePrefix("customize eight:");
        //用来设置线程池关闭的时候等待所有任务都完成(可以设置时间)
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //设置上面的时间
        threadPoolTaskExecutor.setAwaitTerminationSeconds(100);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }
    /**
     * 自定义异常处理类
     * @author hry
     *
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        //手动处理捕获的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            System.out.println("-------------》》》捕获线程异常信息");
            log.info("Exception message - " + throwable.getMessage());
            log.info("Method name - " + method.getName());
            for (Object param : obj) {
                log.info("Parameter value - " + param);
            }
        }
    }
}
