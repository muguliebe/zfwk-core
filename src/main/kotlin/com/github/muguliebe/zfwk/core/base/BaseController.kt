package com.github.muguliebe.zfwk.core.base

import ch.qos.logback.classic.Logger
import com.github.muguliebe.zfwk.core.component.ZContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

open class BaseController : BaseObject() {

    protected val log = LoggerFactory.getLogger(this::class.java) as Logger

}
