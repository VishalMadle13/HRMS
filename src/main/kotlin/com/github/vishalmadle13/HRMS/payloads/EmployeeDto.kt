package com.github.vishalmadle13.HRMS.payloads

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@NoArgsConstructor
@AllArgsConstructor
@Data
data class EmployeeDto (
    var id : Long ? = null,
    var firstName : String = "",
    var middleName : String="",
    var lastName : String="",
    var dateOfBirth: LocalDate = LocalDate.now(),
    var gender : String="",
    var personalMail : String="",
    var phoneNumber  : String="",
    var address : String="",
    var joinDate : LocalDate= LocalDate.now(),
    var department : DepartmentDto = DepartmentDto(),
    var position: PositionDto= PositionDto(),
    var organisationMail : String = ""
    )