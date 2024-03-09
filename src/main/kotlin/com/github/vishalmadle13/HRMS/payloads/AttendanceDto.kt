package com.github.vishalmadle13.HRMS.payloads

import java.time.LocalDate
import java.util.Date

data class AttendanceDto (
    val id : Long ? = null,
    val employee : EmployeeDto,
    val date : LocalDate,
    val clockInTime : Date,
    val clockOutTime: Date
)