package com.github.vishalmadle13.HRMS.entites

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.Date

@Entity
data class PerformanceReview (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long ? = null,
    @ManyToOne @JoinColumn(name="employee_id", nullable = false)
    val employee : Employee,
    val reviewDate : Date,
    val rating : Int,
    val comments : String
    )