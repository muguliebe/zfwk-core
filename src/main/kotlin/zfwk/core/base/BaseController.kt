package zfwk.core.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import zfwk.core.component.ZContext

open class BaseController : BaseObject() {

    protected val log = LoggerFactory.getLogger(this::class.java) as Logger

    @Autowired lateinit var context: ZContext

}
