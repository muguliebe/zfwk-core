package com.github.muguliebe.zfwk.core.component

import com.github.muguliebe.zfwk.core.model.pojo.ContextArea
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class ZContext {
    var area: ContextArea = ContextArea()
}
