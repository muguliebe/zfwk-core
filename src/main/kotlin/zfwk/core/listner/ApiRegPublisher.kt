package zfwk.core.listner

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import zfwk.core.event.ApiRegInfo
import zfwk.core.event.ApiRegInfos
import zfwk.zutils.logger
import java.lang.reflect.Method
import kotlin.system.measureTimeMillis

@Component
class ApiRegPublisher {
    val log = logger()

    @Autowired lateinit var publisher: ApplicationEventPublisher
    @Autowired lateinit var handlerMapping: RequestMappingHandlerMapping

    @EventListener
    fun ready(event: ApplicationReadyEvent) {
        var cntApi = handlerMapping.handlerMethods.size
        val elapsed = measureTimeMillis {

            val regists: MutableList<ApiRegInfo> = mutableListOf<ApiRegInfo>()
            for (pair in handlerMapping.handlerMethods) {
                regists.addAll(getApiRegisterInfo(pair))
            }

            val publish = ApiRegInfos(list = regists)
            publisher.publishEvent(publish)
        }
        log.info("gathered api[cnt($cntApi)] info passed:$elapsed ms")
    }

    fun getApiRegisterInfo(pair: MutableMap.MutableEntry<RequestMappingInfo, HandlerMethod>): List<ApiRegInfo> {
        val result = mutableListOf<ApiRegInfo>()
        val rmi: RequestMappingInfo = pair.key
        if (rmi.pathPatternsCondition == null || rmi.pathPatternsCondition!!.patterns.isEmpty()) {
            return listOf()
        }

        val hm: Method = pair.value.method
        val className = hm.declaringClass.canonicalName
        val funcName = hm.name.split("#").last()
        var methods = mutableListOf<String>() // http method


        val urls = rmi.pathPatternsCondition!!.patterns.map { it.patternString }


        if (urls.contains("/error")) {
            return listOf()
        } else if (rmi.methodsCondition.isEmpty) {
            methods.addAll(listOf("GET", "PUT", "POST", "PATCH", "DELETE"))
        } else {
            rmi.methodsCondition.methods.forEach {
                methods.add(it.name.replace("[", "").replace("]", ""))
            }
        }

        for (url in urls) {
            for (m in methods) {
                val a = ApiRegInfo(
                    className = className,
                    funName = funcName,
                    method = m,
                    url = url
                )
                result.add(a)
            }
        }

        return result
    }

}
