package com.github.vishalm1029.HRMS.security

import com.github.vishalm1029.HRMS.exceptions.AuthenticationFailedException
import com.github.vishalm1029.HRMS.security.jwt.JwtUtils
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/authentication")
//@CrossOrigin(origins = ["http://localhost:4200"])
@RequiredArgsConstructor
internal class AuthenticationController(
    @Autowired
    private val authenticationManager: AuthenticationManager,
    @Autowired
    private val jwtUtils: JwtUtils
) {


    @PostMapping("/sign-in")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest?): ResponseEntity<JwtResponse> {
        println(loginRequest.toString())
        println(authenticationManager.toString())
        if (loginRequest?.username == null || loginRequest.password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

        val authentication = try {
            authenticationManager?.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.username,
                    loginRequest.password
                )

            )
        } catch (ex: AuthenticationException) {
            // Handle authentication failure
            return throw AuthenticationFailedException()
        }

        println(authentication.toString())

        // Check if authentication is successful
        if (authentication?.isAuthenticated != true) {
            // Handle unsuccessful authentication
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
            throw AuthenticationFailedException()
        }

        SecurityContextHolder.getContext().authentication = authentication

        val userDetails = authentication.principal as? UserDetails
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        println(userDetails)

        val roles = userDetails.authorities.map { it.authority }
        println(roles)

        val jwt: String = jwtUtils?.generateJwtToken(authentication) ?: ""
        println(jwt)

        return ResponseEntity.ok<JwtResponse>(
            JwtResponse(
                jwt,
                userDetails.username,
                roles
            )
        )
    }

}