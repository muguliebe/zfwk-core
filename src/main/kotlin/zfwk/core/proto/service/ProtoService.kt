package zfwk.core.proto.service

import org.springframework.stereotype.Service
import zfwk.core.base.BaseService
import zfwk.core.proto.model.ProtoModel

@Service
class ProtoService : BaseService() {

    fun test(): ProtoModel {
        log.info("test start")

        val result = ProtoModel(one = "one", two = "two")

        log.info("test end")
        return result
    }

}
