package com.github.vishalmadle13.HRMS.payloads


data class PayrollDto(
     val id : Long ? = null,
    val employee: EmployeeDto,
    val month : Int,
    val year : String,
    val basicSalary : Long,
    val deduction : Long,
    val netSalary : Long
)