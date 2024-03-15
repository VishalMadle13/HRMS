package com.github.vishalm1029.HRMS.entites

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.annotation.Nullable
import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Employee(
    @Id
    var id: Long? = null,
    var firstName: String = "",
    var middleName: String = "",
    var lastName: String = "",
    @Nullable
    var dateOfBirth: LocalDate = LocalDate.now(),
    var gender: String = "",
    var personalMail: String = "",
    var phoneNumber: String = "",
    var address: String = "",
    var joinDate: LocalDate = LocalDate.now(),
    @ManyToOne @JoinColumn(name = "department_id") @JsonIgnore
    var department: Department = Department(),
    @ManyToOne @JoinColumn(name = "position_id") @JsonIgnore
    var position: Position = Position(),
    var organisationMail: String = ""
) {
    override fun toString(): String {
        return "Employee(id=$id, firstName='$firstName', lastName='$lastName',dateOfBirth=$dateOfBirth," +
                " gender=$gender, personalMail=$personalMail, organisationMail=$organisationMail,phoneNumber=$phoneNumber,address=$address," +
                " joinDate=$joinDate,department=${department.id}, position=${position.id} "
    }
}