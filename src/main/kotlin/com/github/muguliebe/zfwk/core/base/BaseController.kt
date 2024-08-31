package com.github.muguliebe.zfwk.core.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

open class BaseController : BaseObject() {

    protected val log = LoggerFactory.getLogger(this::class.java) as Logger

}
