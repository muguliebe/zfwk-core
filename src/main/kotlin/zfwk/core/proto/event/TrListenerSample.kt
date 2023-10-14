package zfwk.core.proto.event

import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import zfwk.core.event.TrEvent
import zfwk.zutils.logger

@Profile("proto")
@Component
class TrListenerSample {
    val log = logger()

    @EventListener
    fun onTrtr(event: TrEvent){
        log.info("listen > ${event.trContext.area.guid}")
    }
}
