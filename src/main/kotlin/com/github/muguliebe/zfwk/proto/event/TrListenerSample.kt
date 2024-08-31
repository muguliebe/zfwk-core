package com.github.muguliebe.zfwk.proto.event

import com.github.muguliebe.zfwk.core.event.TrEvent
import com.github.muguliebe.zfwk.zutils.logger
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Profile("proto")
@Component
class TrListenerSample {
    val log = logger()

    @EventListener
    fun onTrtr(event: TrEvent){
        log.info("listen > ${event.trContext.common.guid}")
    }
}
