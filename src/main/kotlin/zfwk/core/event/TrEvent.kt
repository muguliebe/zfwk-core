package zfwk.core.event

import zfwk.core.component.ZContext

data class TrEvent(
    val type: TrEventType,
    val trContext: ZContext
)

enum class TrEventType {
    AFTER
}
