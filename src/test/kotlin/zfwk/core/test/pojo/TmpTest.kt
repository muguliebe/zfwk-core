package zfwk.core.test.pojo

import org.junit.jupiter.api.Test

class TmpTest {

    @Test
    fun test(){
        (1..10).toList().forEach {
            println("it = ${it}")
        }
    }
}
