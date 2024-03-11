package com.github.vishalmadle13.HRMS.payloads

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Data
data class PayrollDto(
    var id : Long ? = null,
    var employee: EmployeeDto = EmployeeDto(),
    var month : Int ? = null,
    var year : String ? = null,
    var basicSalary : Long ? = null,
    var deduction : Long ? = null,
    var netSalary : Long ? = null
)