package zfwk.core.proto.event

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import zfwk.core.event.dto.TrEvent
import zfwk.zutils.logger

@Component
class TrListenerImpl {
    val log = logger()

    @EventListener
    fun trEvent(event:TrEvent){
        log.info("listen > ${event.trContext.area.guid}")
    }
}
