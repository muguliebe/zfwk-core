package com.github.muguliebe.zfwk.proto.service

import com.github.muguliebe.zfwk.core.base.BaseService
import com.github.muguliebe.zfwk.proto.model.ProtoModel
import com.github.muguliebe.zfwk.zutils.EtcUtils
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Profile("proto")
@Service
class ProtoService : BaseService() {

    fun test(): ProtoModel {
        log.info("test start")

        val result = ProtoModel(one = "one", two = "two")

        val hostName = EtcUtils.hostName()
        log.info("hostName=$hostName")
        log.info("test end")
        return result
    }

}
