package com.github.muguliebe.zfwk.core.test.base

import com.github.muguliebe.zfwk.core.proto.ZAppMain
import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [ZAppMain::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("proto", "test")
@Execution(ExecutionMode.CONCURRENT)
class BaseSpringTest : FunSpec() {
    @Autowired lateinit var ctx: ApplicationContext
    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var template: TestRestTemplate

    @BeforeEach
    fun before() {
        println("before")
    }

}
