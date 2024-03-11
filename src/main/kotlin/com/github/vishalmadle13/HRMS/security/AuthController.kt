package com.github.vishalmadle13.HRMS.security

//import JwtHelper
import JwtHelper
import com.github.vishalmadle13.HRMS.utils.JwtRequest
import com.github.vishalmadle13.HRMS.utils.JwtResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Autowired
    private val manager: AuthenticationManager? = null

    @Autowired
    private val helper: JwtHelper? = null
    private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)
    @PostMapping("/login")
    fun login(@RequestBody request: JwtRequest): ResponseEntity<JwtResponse> {
        println(request.email)
        println(request.password)
        doAuthenticate(request.email!!, request.password!!)
        val userDetails = userDetailsService!!.loadUserByUsername(request.email)
        val token = helper!!.generateToken(userDetails)
        var response: JwtResponse = JwtResponse()
        response.jwtToken = token
        response.username = userDetails.username

        return ResponseEntity<JwtResponse>(response, HttpStatus.OK)
    }

    private fun doAuthenticate(email: String, password: String) {
        println("Authentication::::::::::::"+email+","+password)
        val authentication = UsernamePasswordAuthenticationToken(email, password)
        try {
            manager!!.authenticate(authentication)
        } catch (e: BadCredentialsException) {
            throw BadCredentialsException(" Invalid Username or Password  !!")
        }
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun exceptionHandler(): String {
        return "Credentials Invalid !!"
    }
}

