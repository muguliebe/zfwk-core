package zfwk.core.proto

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import zfwk.zutils.logger


@SpringBootApplication(
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
