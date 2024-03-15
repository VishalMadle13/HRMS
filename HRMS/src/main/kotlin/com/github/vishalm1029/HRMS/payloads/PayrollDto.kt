package com.github.vishalm1029.HRMS.payloads

import jakarta.validation.constraints.Max
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@NoArgsConstructor
@AllArgsConstructor
@Data
data class PayrollDto(
    var id: Long? = null,

    @field:NotNull(message = "Employee cannot be null")
    var employee: EmployeeDto = EmployeeDto(),

    @field:NotNull(message = "Month cannot be null")
    @field:Min(value = 1, message = "Month must be a valid positive number")
    @field:Max(value = 12, message = "Month must be a valid positive number")
    var month: Int? = null,

    @field:NotNull(message = "Year cannot be null")
    @field:Min(value = 2000, message = "Year must be greater than or equal to 2000")
    @field:Max(value = 3000, message = "Year must be less than or equal to 3000")
    var year: Int? = null,

    @field:NotNull(message = "Basic salary cannot be null")
    @field:Min(value = 0, message = "Basic salary must be a non-negative number")
    var basicSalary: Long? = null,

    @field:NotNull(message = "Deduction cannot be null")
    @field:Min(value = 0, message = "Deduction must be a non-negative number")
    var deduction: Long? = null,

    @field:NotNull(message = "Net salary cannot be null")
    @field:Min(value = 0, message = "Net salary must be a non-negative number")
    var netSalary: Long? = null
)
