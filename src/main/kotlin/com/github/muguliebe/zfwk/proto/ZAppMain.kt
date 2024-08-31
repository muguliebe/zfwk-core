package com.github.muguliebe.zfwk.proto

import com.github.muguliebe.zfwk.zutils.logger
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment


@SpringBootApplication(
    scanBasePackages = [
        "com.github.muguliebe.zfwk.core",
        "com.github.muguliebe.zfwk.proto"
    ],
    exclude = [DataSourceAutoConfiguration::class,  JpaRepositoriesAutoConfiguration::class, HibernateJpaAutoConfiguration::class]
)
class ZAppMain {
    companion object {
        private val log = logger()

        @JvmStatic
        fun main(args: Array<String>) {
            val appContext = runApplication<ZAppMain>(*args) {
                setBannerMode(Banner.Mode.OFF)
            }
            val env = appContext.environment
            val activeProfiles = env.activeProfiles.joinToString(", ")
            log.info("ZAppMain Started with profiles: ${activeProfiles}")

        }
    }
}
