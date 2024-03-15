package com.github.vishalm1029.HRMS.payloads

import jakarta.validation.constraints.Email
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past

@NoArgsConstructor
@AllArgsConstructor
@Data
data class EmployeeDto(
    var id: Long? = null,

    @field:NotBlank(message = "First name cannot be blank")
    var firstName: String = "",

    var middleName: String = "",

    @field:NotBlank(message = "Last name cannot be blank")
    var lastName: String = "",

    @field:Past(message = "Date of birth must be in the past")
    var dateOfBirth: LocalDate = LocalDate.now(),

    @field:NotBlank(message = "Gender cannot be blank")
    var gender: String = "",

    @field:NotBlank(message = "Personal email cannot be blank")
    @Email
    var personalMail: String = "",

    @field:NotBlank(message = "Phone number cannot be blank")
    var phoneNumber: String = "",

    @field:NotBlank(message = "Address cannot be blank")
    var address: String = "",

    @field:Past(message = "Join date must be in the past")
    var joinDate: LocalDate = LocalDate.now(),

    @field:NotNull(message = "Department cannot be null")
    var department: DepartmentDto = DepartmentDto(),

    @field:NotNull(message = "Position cannot be null")
    var position: PositionDto = PositionDto(),

    @field:NotBlank(message = "Organisation email cannot be blank")
    @Email
    var organisationMail: String = ""
)
