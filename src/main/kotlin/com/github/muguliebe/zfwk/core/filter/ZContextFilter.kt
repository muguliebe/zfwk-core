package com.github.muguliebe.zfwk.core.filter

import com.github.muguliebe.zfwk.core.component.ZContext
import com.github.muguliebe.zfwk.core.model.pojo.ContextArea
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class ZContextFilter :OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val zContext = ZContext.getInstance()
            zContext.common = initializeContextArea()

            filterChain.doFilter(request, response)
        } finally {
            ZContext.removeInstance()
        }
    }

    private fun initializeContextArea(): ContextArea {
        // ContextArea 초기화 로직
        return ContextArea()
    }
}
