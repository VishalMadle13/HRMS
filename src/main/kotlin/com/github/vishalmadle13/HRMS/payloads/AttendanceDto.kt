package com.github.vishalmadle13.HRMS.payloads

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.Date
@NoArgsConstructor
@AllArgsConstructor
@Data
data class AttendanceDto (
    var id : Long ? = null,
    var employee : EmployeeDto = EmployeeDto(),
    var date : LocalDate = LocalDate.now(),
    var clockInTime : Date = Date(),
    var clockOutTime: Date = Date()
)