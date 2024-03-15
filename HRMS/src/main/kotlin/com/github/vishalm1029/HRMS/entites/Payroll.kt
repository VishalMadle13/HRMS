package com.github.vishalm1029.HRMS.entites

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Payroll(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,
    @ManyToOne @JoinColumn(name = "employee_id", nullable = false)
    var employee: Employee = Employee(),
    var month: Int? = null,
    var year: Int? = null,
    var basicSalary: Long? = null,
    var deduction: Long? = null,
    var netSalary: Long? = null
)