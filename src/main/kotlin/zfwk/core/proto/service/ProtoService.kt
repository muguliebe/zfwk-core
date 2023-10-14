package zfwk.core.proto.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import zfwk.core.base.BaseService
import zfwk.core.proto.model.ProtoModel
import zfwk.zutils.EtcUtils

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
