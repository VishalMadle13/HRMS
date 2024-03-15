package com.github.vishalm1029.HRMS.security

import com.github.vishalm1029.HRMS.security.jwt.AuthTokenFilter
import com.github.vishalm1029.HRMS.security.jwt.JwtUtils
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
internal class WebSecurityConfig(
    @Autowired
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    @Autowired
    private val jwtUtils: JwtUtils
) {

    //    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .cors(Customizer.withDefaults())
//            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
//            .exceptionHandling { exception: ExceptionHandlingConfigurer<HttpSecurity?> ->
//                exception.authenticationEntryPoint(
//                    authenticationEntryPoint
//                )
//            }
//            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
//                session.sessionCreationPolicy(
//                    SessionCreationPolicy.STATELESS
//                )
//            }
//            .authorizeHttpRequests(
//                Customizer { authorizeHttpRequestsCustomizer: AuthorizationManagerRequestMatcherRegistry ->
//                    authorizeHttpRequestsCustomizer
//                        .requestMatchers("/api/authentication/**").permitAll()
//                        .requestMatchers("/api/content/public/**").permitAll()
//                        .anyRequest().authenticated()
//                }
//            )
//            .userDetailsService(userDetailsService())
//            .addFilterBefore(
//                authenticationJwtTokenFilter(),
//                UsernamePasswordAuthenticationFilter::class.java
//            )
//        return http.build()
//    }
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            cors {}
            csrf { disable() }
            exceptionHandling { authenticationEntryPoint  }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            authorizeRequests {
                authorize("/api/authentication/**", permitAll)
                authorize("/api/content/public/**", permitAll)
                authorize("/api/**", authenticated)
                authorize(anyRequest, authenticated)

            }
//            httpBasic { defaults }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(filter = authenticationJwtTokenFilter())
//            (JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        }
        return http.build()
    }

    @Bean
    fun authenticationJwtTokenFilter(): AuthTokenFilter {
        return AuthTokenFilter(jwtUtils, userDetailsService())
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(
        authenticationConfiguration: AuthenticationConfiguration
    ): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user1 = User
            .builder()
            .username("vishal")
            .password("password123")
            .roles("ADMIN")
            .build()

        val user2 = User
            .builder()
            .username("sagar")
            .password("password123")
            .roles("USER")
            .build()

        val user3 = User
            .builder()
            .username("aman")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(linkedSetOf(user1, user2, user3))
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}