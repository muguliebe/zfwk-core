package zfwk.core.test.base

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import zfwk.core.proto.ZAppMain

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [ZAppMain::class])
@AutoConfigureMockMvc
@ActiveProfiles("proto", "test")
class BaseSpringTest {

    @Autowired lateinit var ctx: ApplicationContext
    @Autowired lateinit var mockMvc: MockMvc
}
