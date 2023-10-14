package zfwk.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import zfwk.zutils.logger

@Configuration
@EnableAsync
class ZfwkConfig {
    val log = logger()
}

