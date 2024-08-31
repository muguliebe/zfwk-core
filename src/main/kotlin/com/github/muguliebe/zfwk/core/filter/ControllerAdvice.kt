package com.github.muguliebe.zfwk.core.filter

import ch.qos.logback.classic.Logger
import com.github.muguliebe.zfwk.core.context.ZContext
import com.github.muguliebe.zfwk.core.event.TrEvent
import com.github.muguliebe.zfwk.core.event.TrEventType
import com.github.muguliebe.zfwk.zutils.DateUtils
import com.github.muguliebe.zfwk.zutils.EtcUtils
import com.github.muguliebe.zfwk.zutils.GuidUtils
import jakarta.servlet.http.HttpServletRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.net.URI
import java.time.OffsetDateTime
import java.time.ZoneId

@Aspect
@Component
class ControllerAdvice {

    companion object {
        private val log = LoggerFactory.getLogger("ControllerAdvice") as Logger
    }

    @Value("\${app.version:0.1}")
    val appVersion: String = ""             // 어플리케이션 버젼

    @Value("\${app.local-pass:false}")
    val isLocalPass: Boolean = false   // local 구동 시, permission 체크를 할 것인가?

    @Autowired lateinit var ctx: ApplicationContext                       // Context
    @Autowired lateinit var publisher: ApplicationEventPublisher

    @Around("PointCutList.allController()")
    fun aroundController(pjp: ProceedingJoinPoint): Any? {

        // init --------------------------------------------------------------------------------------------------------
        val result: Any?
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val signatureName = "${pjp.signature.declaringType.simpleName}.${pjp.signature.name}"
        val context = ZContext.getInstance()
        val common = context.common

        // main --------------------------------------------------------------------------------------------------------
        try {
            log.info("[${common.guid}] >>>>>  controller start [$signatureName() from [${common.remoteIp}] by ${common.method} ${common.path}")
            result = pjp.proceed()
        } catch (e: Exception) {
            log.info("[${common.guid}] <<<<<  controller   end [$signatureName() from [${common.remoteIp}] [${common.elapse}ms] with Error [${e.javaClass.simpleName}]")
            throw e
        }
        log.info("[${common.guid}] <<<<<  controller   end [$signatureName() from [${common.remoteIp}] [${common.elapse}ms]")


        // end ---------------------------------------------------------------------------------------------------------
        log.info("controller end")

        GlobalScope.async {
            publisher.publishEvent(TrEvent(TrEventType.AFTER, context))
        }

        return result
    }

}
