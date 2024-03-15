package com.github.vishalm1029.HRMS.entites

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["employee_id", "date"])])
data class Attendance(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    var employee: Employee = Employee(),
    var date: LocalDate = LocalDate.now(),
    var clockInTime: Date = Date(),
    var clockOutTime: Date = Date(),
)