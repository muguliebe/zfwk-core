package com.github.muguliebe.zfwk.proto.controller

import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Profile("proto")
@RestController
@RequestMapping("/zfwk/other")
class ProtoOtherController {

    @RequestMapping
    fun requestMapping() {
    }

    @GetMapping("/path/:path")
    fun pathVariable() {
    }

    @GetMapping("/other1", "/other2")
    fun multipleUrl() {
    }


}
