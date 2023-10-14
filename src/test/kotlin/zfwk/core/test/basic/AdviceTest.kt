package zfwk.core.test.basic

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import zfwk.core.test.base.BaseSpringTest
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class AdviceTest : BaseSpringTest() {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    fun yo() {
        runBlocking {
            val elapsed = measureTimeMillis {
                val block = {
                    val res = template.getForEntity("/zfwk/proto/nothing", Any::class.java)
                    println(res.statusCode)
                }


                val threads = arrayListOf<Deferred<Unit>>()
                (0..100).toList().parallelStream()
                    .forEach {
                        val r = GlobalScope.async { block() }
                        threads.add(r)
                    }
                threads.awaitAll()

            }
            println("elapsed = $elapsed")
        }
    }

}
