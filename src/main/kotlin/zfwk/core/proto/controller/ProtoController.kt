package zfwk.core.proto.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zfwk.core.base.BaseController
import zfwk.core.proto.service.ProtoService

@Profile("proto")
@RestController
@RequestMapping("/zfwk/proto")
class ProtoController : BaseController() {

    @Autowired lateinit var service: ProtoService

    @GetMapping
    fun protoGet(): String {
        log.info("protoGet start")

        val result = service.test()
        log.info("result=$result")

        log.info("protoGet end")
        return "ok"
    }

}
