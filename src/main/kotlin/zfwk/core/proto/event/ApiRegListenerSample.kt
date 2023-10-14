package zfwk.core.proto.event

import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import zfwk.core.event.ApiRegInfos
import zfwk.zutils.logger

@Profile("proto")
@Component
class ApiRegListenerSample {
    val log = logger()

    @EventListener
    fun onApiReg(event: ApiRegInfos) {
        event.list.forEachIndexed { index, e ->
            log.info("[$index]:{$e}", )
        }
    }
}
