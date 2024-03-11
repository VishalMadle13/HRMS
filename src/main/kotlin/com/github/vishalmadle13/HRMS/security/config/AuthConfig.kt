package com.github.vishalmadle13.HRMS.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
class AuthConfig {
    @Bean
    fun userDetailsService(): UserDetailsService {
        val user1: UserDetails =
            User
                .builder().username("vishal")
                    .password(passwordEncoder()
                        .encode("vishal"))
                            .roles("ADMIN").build()
        val user2: UserDetails =
            User
                .builder().username("sagar")
                .password(passwordEncoder()
                    .encode("123"))
                .roles("USER").build()
        return InMemoryUserDetailsManager(user1,user2)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(builder: AuthenticationConfiguration): AuthenticationManager {
        return builder.getAuthenticationManager()
    }
}