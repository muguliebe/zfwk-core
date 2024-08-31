package com.github.muguliebe.zfwk.core.context

import org.springframework.core.task.TaskDecorator

class ContextCopyingDecorator : TaskDecorator {
    override fun decorate(runnable: Runnable): Runnable {
        val context = ZContext.getInstance()

        return Runnable {
            try {
                ZContext.threadLocal.set(context)
                runnable.run()

            } finally {
                ZContext.removeInstance()
            }
        }
    }
}
