package zfwk.core.test.pojo

import org.junit.jupiter.api.Test
import zfwk.core.test.base.BaseTest

class TmpTest : BaseTest() {

    @Test
    fun test(){
        (1..10).toList().forEach {
            log.info("it = ${it}")
        }
    }
}
