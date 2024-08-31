package com.github.muguliebe.zfwk.proto.event

import com.github.muguliebe.zfwk.core.event.ApiRegEvent
import com.github.muguliebe.zfwk.zutils.logger
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Profile("proto")
@Component
class ApiRegListenerSample {
    val log = logger()

    @EventListener
    fun onApiReg(event: ApiRegEvent) {
        event.list.forEachIndexed { index, e ->
            log.trace("[$index]:{$e}", )
        }
    }
}
