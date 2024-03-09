package com.github.vishalmadle13.HRMS.exceptions

 import com.github.vishalmadle13.HRMS.utils.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.function.Consumer

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundExceptionHandler(ex: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        var message = ex.message
        var apiResponse = ApiResponse(message, false)
        return ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlleMethodArgNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val response: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { objectError: ObjectError ->
            val fieldName = (objectError as FieldError).field
            val message = objectError.getDefaultMessage()
            response[fieldName] = message
            println("field:"+fieldName +"  msg:"+message)
        })
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}

