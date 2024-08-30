package com.github.muguliebe.zfwk.core.proto

import com.github.muguliebe.zfwk.zutils.logger
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(
    scanBasePackages = ["com.github.muguliebe.zfwk"],
    exclude = [DataSourceAutoConfiguration::class]
)
class ZAppMain {
    companion object {
        private val log = logger()

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ZAppMain>(*args) {
                setBannerMode(Banner.Mode.OFF)
            }
            log.info("ZAppMain Started")

        }
    }
}
