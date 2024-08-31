package com.github.muguliebe.zfwk.core.base

import ch.qos.logback.classic.Logger
import com.github.muguliebe.zfwk.core.config.DataSourceAutoConfigExcludedCondition
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Conditional
import org.springframework.transaction.annotation.Transactional

@Conditional(DataSourceAutoConfigExcludedCondition::class)
@Transactional
abstract class BaseService : BaseObject() {

    protected final val log = LoggerFactory.getLogger(this::class.java) as Logger

}
