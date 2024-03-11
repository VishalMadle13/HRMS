package com.github.vishalmadle13.HRMS.security

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException?) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer = response.writer
        if (authException != null) {
            writer.println("Access Denied !! " + authException.message)
        }
    }
}