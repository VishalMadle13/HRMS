package com.github.vishalm1029.HRMS.security.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtils {
    @Value("\${hrms.jwt-secret}")
    private val jwtSecret: String? = null

    @Value("\${hrms.jwt-expiration}")
    private val jwtExpiration = 0
    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetails
        return Jwts.builder()
            .subject(userPrincipal.username)
            .claim(USERNAME_CLAIM, userPrincipal.username)
            .issuedAt(Date())
            .expiration(Date(Date().time + jwtExpiration))
            .signWith(key())
            .compact()
    }

    private fun key(): SecretKey {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))
    }

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts
            .parser()
            .verifyWith(key())
            .build()
            .parseSignedClaims(token)
            .payload
            .get(USERNAME_CLAIM, String::class.java)
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts
                .parser()
                .verifyWith(key())
                .build()
                .parse(authToken)
            return true
        } catch (e: MalformedJwtException) {
            LOGGER.error("Invalid JWT: {}", e.message)
        } catch (e: ExpiredJwtException) {
            LOGGER.error("JWT has expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            LOGGER.error("JWT is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            LOGGER.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(JwtUtils::class.java)
        private const val USERNAME_CLAIM = "username"
    }
}