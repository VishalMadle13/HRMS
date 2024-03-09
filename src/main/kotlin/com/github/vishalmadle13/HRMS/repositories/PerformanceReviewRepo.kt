package com.github.vishalmadle13.HRMS.repositories

import com.github.vishalmadle13.HRMS.entites.PerformanceReview
import lombok.extern.java.Log
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceReviewRepo : JpaRepository<PerformanceReview, Long> {
}