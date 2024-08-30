package com.github.muguliebe.zfwk.core.component

import com.github.muguliebe.zfwk.core.model.pojo.ContextArea

class ZContext private constructor() {

    var common: ContextArea = ContextArea()

    companion object {
        private val threadLocal = ThreadLocal<ZContext>()

        fun getInstance(): ZContext {
            return threadLocal.get() ?: ZContext().also { threadLocal.set(it) }
        }

        fun removeInstance() {
            threadLocal.remove()
        }
    }
}
