package com.github.vishalm1029.HRMS.security.jwt

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver


@Component
@RequiredArgsConstructor
internal class AuthEntryPoint : AuthenticationEntryPoint {
    private val handlerExceptionResolver: HandlerExceptionResolver? = null
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authenticationException: AuthenticationException
    ) {
        LOGGER.error("Unauthorized error: {}", authenticationException.message)
        println(authenticationException.message)
        handlerExceptionResolver!!.resolveException(request, response, null, authenticationException)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(
            AuthEntryPoint::class.java
        )
    }
}