package com.github.vishalmadle13.HRMS.entites

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Payroll(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long ? = null,
    @ManyToOne @JoinColumn(name="employee_id", nullable = false)
    val employee: Employee,
    val month : Int,
    val year : String,
    val basicSalary : Long,
    val deduction : Long,
    val netSalary : Long
)