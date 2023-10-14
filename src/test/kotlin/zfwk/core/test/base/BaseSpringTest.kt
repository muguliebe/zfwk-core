package zfwk.core.test.base

import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import zfwk.core.proto.ZAppMain

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [ZAppMain::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("proto", "test")
@Execution(ExecutionMode.CONCURRENT)
class BaseSpringTest : FunSpec() {
    @Value(value = "\${local.server.port}") private var port: Int? = null
    @Autowired lateinit var ctx: ApplicationContext
    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var template: TestRestTemplate

    init{
//        extension(SpringExtension)
    }

    @BeforeEach
    fun before() {
        println("before")
    }

}
