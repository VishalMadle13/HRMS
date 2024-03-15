package com.github.vishalm1029.HRMS.payloads.attendance


import com.github.vishalm1029.HRMS.payloads.EmployeeDto
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.Date
import jakarta.validation.constraints.NotNull

@NoArgsConstructor
@AllArgsConstructor
@Data
data class AttendanceDto(
    var id: Long? = null,
    @field:NotNull(message = "Employee cannot be null")
    var employee: EmployeeDto = EmployeeDto(),

    @field:NotNull(message = "Date cannot be null")
    var date: LocalDate = LocalDate.now(),

    var clockInTime: Date = Date(),
    var clockOutTime: Date = Date()
)