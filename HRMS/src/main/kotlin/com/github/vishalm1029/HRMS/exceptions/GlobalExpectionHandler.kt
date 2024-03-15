package com.github.vishalm1029.HRMS.exceptions

import com.github.vishalm1029.HRMS.utils.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.URI
import java.time.Instant
import java.util.function.Consumer

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundExceptionHandler(ex: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        val message = ex.message
        val apiResponse = ApiResponse(message, false)
        return ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceAlreadyExistException::class)
    fun resourceNotFoundExceptionHandler(ex: ResourceAlreadyExistException): ResponseEntity<ApiResponse> {
        val message = ex.message
        val apiResponse = ApiResponse(message, false)
        return ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlleMethodArgNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val response: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { objectError: ObjectError ->
            val fieldName = (objectError as FieldError).field
            val message = objectError.getDefaultMessage()
            response[fieldName] = message
        })
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(e: AuthenticationFailedException): ProblemDetail? {
        val problemDetail = ProblemDetail
            .forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.message!!)
        problemDetail.title = "Authentication Error"
        problemDetail.type = URI.create("http://localhost:8080/authentication_error.html")
        problemDetail.setProperty("errorCategory", "Authentication")
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }
}

