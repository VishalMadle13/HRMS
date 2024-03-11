package com.github.vishalmadle13.HRMS.security.config

import JwtAuthenticationFilter
import JwtHelper
import com.github.vishalmadle13.HRMS.security.JwtAuthenticationEntryPoint
 import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfig {
    @Bean
    fun jwtAuthenticationFilter(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter()
    }
    @Bean
    fun jwtHelper(): JwtHelper {
        return JwtHelper()
    }
    @Autowired
    private lateinit var point: JwtAuthenticationEntryPoint
//
//    @Autowired
//    private lateinit var filter: JwtAuthenticationFilter
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf: CsrfConfigurer<HttpSecurity> -> csrf.disable() }
            .authorizeRequests()
            .requestMatchers("/api/auth/login").permitAll()
            .requestMatchers("/api/attendance/**", "/api/payroll/**", "/api/performance-review**", "/api/position/**", "/api/employee/**", "/api/department/**").hasRole("USER")
            .requestMatchers("**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .exceptionHandling { ex: ExceptionHandlingConfigurer<HttpSecurity?> ->
                ex.authenticationEntryPoint(
                    point
                )
            }
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}