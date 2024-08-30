package com.github.muguliebe.zfwk.core.listner

import com.github.muguliebe.zfwk.core.event.ApiRegEvent
import com.github.muguliebe.zfwk.core.event.ApiRegInfo
import com.github.muguliebe.zfwk.zutils.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.lang.reflect.Method
import kotlin.system.measureTimeMillis

@Component
class ApiRegPublisher {
    val log = logger()

    @Autowired lateinit var publisher: ApplicationEventPublisher
    @Qualifier("requestMappingHandlerMapping") @Autowired lateinit var handlerMapping: RequestMappingHandlerMapping

    @EventListener
    fun ready(event: ApplicationReadyEvent) {
        val cntApi = handlerMapping.handlerMethods.size
        val elapsed = measureTimeMillis {

            val regists: MutableList<ApiRegInfo> = mutableListOf<ApiRegInfo>()
            for (pair in handlerMapping.handlerMethods) {
                regists.addAll(getApiRegisterInfo(pair))
            }

            val publish = ApiRegEvent(list = regists)
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
        val methods = mutableListOf<String>() // http method


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
