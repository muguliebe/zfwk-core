package zfwk.core.filter

import ch.qos.logback.classic.Logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import zfwk.core.component.Commons
import zfwk.zutils.DateUtils
import java.net.InetAddress
import java.net.URI
import java.net.UnknownHostException
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*
import jakarta.servlet.http.HttpServletRequest

@Aspect
@Component
class ControllerAdvice {

    companion object {
        private val log = LoggerFactory.getLogger("ControllerAdvice") as Logger
    }

    var isSetVariable = false                                             // 전역변수를 셋팅하였는가?
    lateinit var hostName: String                                         // 서버명
    lateinit var appName: String                                          // 어플리케이션명
    lateinit var profile: String                                          // 프로파일
    var bDev: Boolean = true                                              // 개발계 여부
    var bLocal: Boolean = true                                            // 로컬 여부
    var bPrd: Boolean = false                                             // 운영계 여부
    @Value("\${app.version:0.1}") val appVersion: String = ""             // 어플리케이션 버젼
    @Value("\${app.local-pass:false}") val isLocalPass: Boolean = false   // local 구동 시, permission 체크를 할 것인가?

    @Autowired lateinit var ctx: ApplicationContext                       // Context
    @Autowired lateinit var commons: Commons                              // Common 영역



    @Around("PointCutList.allController()")
    fun aroundController(pjp: ProceedingJoinPoint): Any? {

        // init --------------------------------------------------------------------------------------------------------
        var result: Any?
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val area = commons.area
        val signatureName = "${pjp.signature.declaringType.simpleName}.${pjp.signature.name}"

        // 전역변수 셋팅
        if (!isSetVariable) {
            setStaticVariable()
        }

        // CommonArea
        setCommonArea(req, pjp)

        // main --------------------------------------------------------------------------------------------------------
        try {
            log.info("[${area.gid}] >>>>>  controller start [$signatureName() from [${area.remoteIp}] by ${area.method} ${area.path}")
            result = pjp.proceed()
        } catch (e: Exception) {
            log.info("[${area.gid}] <<<<<  controller   end [$signatureName() from [${area.remoteIp}] [${area.elapse}ms] with Error [${e.javaClass.simpleName}]")
            throw e
        }
        log.info("[${area.gid}] <<<<<  controller   end [$signatureName() from [${area.remoteIp}] [${area.elapse}ms]")


        // end ---------------------------------------------------------------------------------------------------------
        log.info("controller end")

        return result
    }

    /**
     * Common Area 설정
     */
    private fun setCommonArea(req: HttpServletRequest, pjp: ProceedingJoinPoint) {

        // Client IP
        var clientIp = req.getHeader("x-forwarded-for")
        clientIp = if (clientIp != null) {
            clientIp.split(",")[0]
        } else {
            req.remoteAddr
        }

        // commonArea
        commons.area.appName = appName
        commons.area.appVer = appVersion
        commons.area.date = DateUtils.currentDate()
        commons.area.gid = UUID.randomUUID().toString()
        commons.area.method = req.method
        commons.area.path = req.requestURI
        commons.area.startDt = OffsetDateTime.now(ZoneId.of("+9"))
        commons.area.remoteIp = clientIp
        commons.area.queryString = req.queryString
        commons.area.hostName = hostName
        commons.area.bDev = bDev
        commons.area.bLocal = bLocal
        commons.area.bPrd = bPrd

        if (req.getHeader("referer") != null) {
            val referrer = req.getHeader("referer")
            commons.area.referrer = URI(referrer).path
        }

    }

    /**
     * 전역 변수 셋팅
     */
    private fun setStaticVariable() {
        // 호스트명 셋팅
        hostName = try {
            InetAddress.getLocalHost().hostName
        } catch (e: UnknownHostException) {
            log.error("err occurred when get hostname: ${e.message}")
            "unknown"
        }

        // 어플리케이션명 셋팅
        val env = ctx.getBean("environment") as Environment

        // 개발계 여부
        bDev = when {
            "dev" in env.activeProfiles -> true
            else -> false
        }

        // 로컬 여부
        bLocal = when {
            "local" in env.activeProfiles -> true
            else -> false
        }

        // 운영계 여부
        bPrd = when {
            "prd" in env.activeProfiles -> true
            else -> false
        }

        // 어플리케이션 명
        appName = when {
            env.containsProperty("app.name") -> env.getProperty("app.name")!!
            else -> "appName"
        }

        profile = appName
        profile += when {
            "prd" in env.activeProfiles -> "-prd"
            "dev" in env.activeProfiles -> "-dev"
            "local" in env.activeProfiles -> "-local"
            else -> "-unknown"
        }

        // 현 함수 재호출 되지 않도록 Marking
        isSetVariable = true

        // logging
        log.info("========== Static Variable ==============")
        log.info("hostName:$hostName")
        log.info("bDev:$bDev")
        log.info("bLocal:$bLocal")
        log.info("bPrd:$bPrd")
        log.info("appName:$appName")
        log.info("profile:$profile")
        log.info("=========================================")
    }
}
