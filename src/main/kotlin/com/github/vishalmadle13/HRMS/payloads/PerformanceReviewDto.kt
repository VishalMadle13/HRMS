package com.github.vishalmadle13.HRMS.payloads

import java.util.*

data class PerformanceReviewDto (
    val id : Long ? = null,
    val employee : EmployeeDto,
    val reviewDate : Date,
    val rating : Int,
    val comments : String
    )