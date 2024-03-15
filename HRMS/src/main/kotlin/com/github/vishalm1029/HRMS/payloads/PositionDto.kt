package com.github.vishalm1029.HRMS.payloads

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class PositionDto(
    @field:NotNull(message = "ID cannot be null")
    var id: String = "",

    @field:Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
    var title: String = "",

    var salary: Long? = null,

    @field:Size(min = 10, message = "Responsibility must be at least 10 characters long")
    var responsibility: String = ""
)
