package com.github.muguliebe.zfwk.core.filter

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class PointCutList {

    @Pointcut("allController()")
    fun pointController() {
    }

    @Pointcut("within(com.github.muguliebe.zfwk.core.base.BaseController+)")
    fun allController() {
    }

}
