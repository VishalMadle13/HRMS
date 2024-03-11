package com.github.vishalmadle13.HRMS.entites

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date

@Entity
data class PerformanceReview (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    var id : Long ? = null,
    @ManyToOne @JoinColumn(name="employee_id", nullable = false)
    var employee : Employee = Employee(),
    var reviewDate : LocalDate = LocalDate.now(),
    var rating : Float = 0f,
    var comments : String = ""
    )