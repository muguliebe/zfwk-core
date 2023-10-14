package zfwk.core.listner

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class StartupListen {

    @EventListener
    fun ready(event: ApplicationReadyEvent) {

    }
}
