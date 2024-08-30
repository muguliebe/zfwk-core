package com.github.muguliebe.zfwk.core.base

import com.github.muguliebe.zfwk.core.component.ZContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

/**
 * 최상위 클래스
 */
abstract class BaseObject {

    @Autowired lateinit var applicationContext: ApplicationContext

    val context= ZContext.getInstance()

}
