package com.github.muguliebe.zfwk.proto.counter

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class HttpRequestCounter {
    @Autowired private lateinit var meterRegistry: MeterRegistry
    private lateinit var httpRequestCounter: Counter

    @PostConstruct
    fun init() {
        httpRequestCounter = Counter.builder("zHttpRequest").register(meterRegistry)
    }

    fun increase() {
        httpRequestCounter.increment()
    }
}
