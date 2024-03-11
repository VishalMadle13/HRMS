package com.github.vishalmadle13.HRMS.repositories

import com.github.vishalmadle13.HRMS.entites.PerformanceReview
import lombok.extern.java.Log
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PerformanceReviewRepo : JpaRepository<PerformanceReview, Long> {
    @Query("SELECT pr FROM PerformanceReview pr WHERE pr.employee.firstName = :employeeName")
    fun findByEmployeeName(@Param("employeeName") employeeName: Long): List<PerformanceReview>

}