package com.github.muguliebe.zfwk.proto.config

import com.github.muguliebe.zfwk.zutils.logger
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.transaction.annotation.EnableTransactionManagement

@Profile("proto")
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableCaching
@EnableScheduling
class ProtoConfig {

    val log = logger()

    @Bean
    fun configProto(): ProtoConfig {
        log.info("protoConfig properly")
        return this
    }

}
