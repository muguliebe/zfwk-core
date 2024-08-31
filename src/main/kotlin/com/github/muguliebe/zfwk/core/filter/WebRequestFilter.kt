package com.github.muguliebe.zfwk.core.filter

import com.github.muguliebe.zfwk.core.context.ZContext
import com.github.muguliebe.zfwk.zutils.DateUtils
import com.github.muguliebe.zfwk.zutils.EtcUtils
import com.github.muguliebe.zfwk.zutils.GuidUtils
import com.github.muguliebe.zfwk.zutils.logger
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.net.URI
import java.time.OffsetDateTime
import java.time.ZoneId

@Component
class WebRequestFilter : OncePerRequestFilter() {

    companion object {
        private val log = logger()
    }

    @Autowired lateinit var ctx: ApplicationContext
    @Autowired lateinit var env: Environment
    var isSetVariable = false     // 전역변수를 셋팅하였는가?
    lateinit var hostName: String // 서버명
    lateinit var appName: String  // 어플리케이션명
    lateinit var profile: String  // 프로파일
    var bDev: Boolean = true      // 개발계 여부
    var bLocal: Boolean = true    // 로컬 여부
    var bPrd: Boolean = false     // 운영계 여부

    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            setCommonArea(req)

            filterChain.doFilter(req, res)
        } finally {
            ZContext.removeInstance()
        }
    }

    /**
     * Common Area 설정
     */
    private fun setCommonArea(req: HttpServletRequest) {
        val common = ZContext.getInstance().common

        if (!isSetVariable) {
            setStaticVariable()
        }

        // Client IP
        var clientIp = req.getHeader("x-forwarded-for")
        clientIp = clientIp?.split(",")?.last() ?: req.remoteAddr

        // commonArea
        common.appName = appName
        common.appVer = DateUtils.currentDateTimeFormat("yyMMdd.HH.mm.ss")
        common.date = DateUtils.currentDateString()
        common.guid = GuidUtils.generate()
        common.method = req.method
        common.path = req.requestURI
        common.startDt = OffsetDateTime.now(ZoneId.of("+8"))
        common.remoteIp = clientIp
        common.queryString = req.queryString
        common.hostName = hostName
        common.bDev = bDev
        common.bLocal = bLocal
        common.bPrd = bPrd

        if (req.getHeader("referer") != null) {
            val referrer = req.getHeader("referer")
            common.referrer = URI(referrer).path
        }

    }

    /**
     * 전역 변수 셋팅
     */
    private fun setStaticVariable() {

        hostName = EtcUtils.hostName()                // 호스트명 셋팅
        bDev = "dev" in env.activeProfiles            // 개발계 여부
        bLocal = "local" in env.activeProfiles        // 로컬 여부
        bPrd = "prd" in env.activeProfiles            // 운영계 여부

        if (env.activeProfiles.contains("proto")) {
            bLocal = true
            bDev = true
        }

        appName = env.getProperty("app.name", "app")  // 어플리케이션 명
        profile = "$appName-${env.activeProfiles.joinToString(",")}" // 프로파일포함 어플리케이션명

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
