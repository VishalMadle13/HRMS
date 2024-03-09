package com.github.vishalmadle13.HRMS.payloads

import com.github.vishalmadle13.HRMS.entites.Employee
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import lombok.*
import org.jetbrains.annotations.NotNull


@Data
@NoArgsConstructor
@AllArgsConstructor
data class PositionDto (
    @field:NotNull
    var id : String = "",
    @field:Size(min=5 , max = 50)
    var title : String = "",
    @field:NotBlank(message = "The salary is required.")
    var salary : Long ? = null,
    @field:Size(min=10)
    var responsibility : String = "",
 )