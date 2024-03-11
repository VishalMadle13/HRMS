package com.github.vishalmadle13.HRMS.entites

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDate
import java.util.Date

@Entity
data class Attendance (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long ? = null,
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    var employee : Employee = Employee(),
    var date : LocalDate = LocalDate.now(),
    var clockInTime : Date = Date(),
    var clockOutTime: Date = Date()
)

