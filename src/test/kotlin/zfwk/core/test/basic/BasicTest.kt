package zfwk.core.test.basic

import org.junit.jupiter.api.Test
import org.springframework.mock.http.server.reactive.MockServerHttpRequest.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import zfwk.core.test.base.BaseSpringTest

class BasicTest :BaseSpringTest() {

    @Test
    fun contextLoads() {}

    @Test
    fun protoCall() {
        mockMvc.perform(MockMvcRequestBuilders.get("/zfwk/proto"))
            .andExpect(status().isOk())
            .andExpect(content().string("ok"))
    }
}
