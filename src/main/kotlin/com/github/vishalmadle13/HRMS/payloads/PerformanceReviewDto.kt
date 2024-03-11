package com.github.vishalmadle13.HRMS.payloads

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@AllArgsConstructor
@NoArgsConstructor
@Data
data class PerformanceReviewDto (
    var id : Long ? = null,
    var employeeId : Long ?= null,
    var employeeFirstName : String ?= null,
    var employeeLastName : String ?= null,
    var reviewDate : LocalDate = LocalDate.now(),
    var rating : Float = 0f,
    var comments : String = ""
    )