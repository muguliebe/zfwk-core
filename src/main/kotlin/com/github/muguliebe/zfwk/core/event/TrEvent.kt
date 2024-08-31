package com.github.muguliebe.zfwk.core.event

import com.github.muguliebe.zfwk.core.context.ZContext


data class TrEvent(
    val type: TrEventType,
    val trContext: ZContext
)

enum class TrEventType {
    AFTER
}
