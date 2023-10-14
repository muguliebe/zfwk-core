package zfwk.core.component

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import zfwk.core.model.pojo.ContextArea

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class ZContext {
    var area: ContextArea = ContextArea()
}
