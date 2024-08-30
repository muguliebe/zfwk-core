package com.github.muguliebe.zfwk.core.test.basic

import com.github.muguliebe.zfwk.core.test.base.BaseSpringTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class BasicTest : BaseSpringTest() {

    @Test
    fun contextLoads() {
    }

    @Test
    fun mock() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/zfwk/proto")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(content().string("ok"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun template() {
        val res = template.getForEntity("/zfwk/proto", String::class.java)
        res.statusCode shouldBe HttpStatusCode.valueOf(200)
    }
}
