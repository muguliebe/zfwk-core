package com.github.muguliebe.zfwk.core.proto.controller

import com.github.muguliebe.zfwk.core.base.BaseController
import com.github.muguliebe.zfwk.core.proto.counter.HttpRequestCounter
import com.github.muguliebe.zfwk.core.proto.service.ProtoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Profile("proto")
@RestController
@RequestMapping("/zfwk/proto")
class ProtoController : BaseController() {

    @Autowired lateinit var service: ProtoService
    @Autowired lateinit var counter: HttpRequestCounter

    @GetMapping
    fun protoGet(): String {
        log.info("protoGet start:${context.common.guid}")

        val result = service.test()
        log.info("result=$result")

        log.info("protoGet end:${context.common.guid}")
        return "ok"
    }

    @GetMapping("/nothing")
    fun nothing(@RequestParam number: Number): Number {
        counter.increase()
        return number
    }

}
