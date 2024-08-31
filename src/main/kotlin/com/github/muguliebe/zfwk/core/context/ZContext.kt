package com.github.muguliebe.zfwk.core.context

import com.github.muguliebe.zfwk.core.model.pojo.ContextArea

class ZContext private constructor() {

    var common: ContextArea = ContextArea()

    companion object {
        val threadLocal = ThreadLocal<ZContext>()

        fun getInstance(): ZContext {
            return threadLocal.get() ?: ZContext().also { threadLocal.set(it) }
        }

        fun removeInstance() {
            threadLocal.remove()
        }
    }
}
