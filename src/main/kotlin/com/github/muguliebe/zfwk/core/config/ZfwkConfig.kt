package com.github.muguliebe.zfwk.core.config

import com.github.muguliebe.zfwk.core.context.ContextCopyingDecorator
import com.github.muguliebe.zfwk.zutils.logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
class ZfwkConfig {
    val log = logger()

    @Bean
    fun configZfwk(): ZfwkConfig {
        log.info("zfwkConfig properly")
        return this
    }

    @Bean(name = ["threadPoolTaskExecutor"])
    fun taskExecutor(): TaskExecutor {
        log.debug("threadPoolTaskExecutor setting start")
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 1000
        taskExecutor.queueCapacity = 1000
        taskExecutor.maxPoolSize = 1000
        taskExecutor.setThreadNamePrefix("Executor-")
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true)
        taskExecutor.setTaskDecorator(ContextCopyingDecorator())
        taskExecutor.setAwaitTerminationSeconds(10)
        log.debug("threadPoolTaskExecutor setting ")
        return taskExecutor
    }

}

