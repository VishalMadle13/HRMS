package com.github.vishalm1029.HRMS.payloads

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.PositiveOrZero
import java.time.LocalDate

data class PerformanceReviewDto(
    var id: Long? = null,

    @field:NotNull(message = "Employee ID cannot be null")
    var employeeId: Long? = null,

    @field:NotBlank(message = "Employee first name cannot be blank")
    var employeeFirstName: String? = null,

    @field:NotBlank(message = "Employee last name cannot be blank")
    var employeeLastName: String? = null,

    @field:PastOrPresent(message = "Review date must be in the past or present")
    var reviewDate: LocalDate = LocalDate.now(),

    @field:PositiveOrZero(message = "Rating must be a positive number or zero")
    var rating: Float = 0f,

    @field:NotBlank(message = "Comments cannot be blank")
    var comments: String = ""
)
