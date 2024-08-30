package com.github.muguliebe.zfwk.core.test.pojo

import com.github.muguliebe.zfwk.core.test.base.BaseTest
import org.junit.jupiter.api.Test

class TmpTest : BaseTest() {

    @Test
    fun test(){
        (1..10).toList().forEach {
            log.info("it = ${it}")
        }
    }
}
