package com.github.vishalmadle13.HRMS.services

import com.github.vishalmadle13.HRMS.entites.PerformanceReview

interface PerformanceReviewService {
    fun getAllPerformanceReview() : List<PerformanceReview>
    fun getPerformanceReviewById(performanceReviewId : Long)  : PerformanceReview ?
    fun addPerformanceReview(performanceReview: PerformanceReview) : PerformanceReview
    fun updatePerformanceReview(performanceReviewId: Long, performanceReview: PerformanceReview) : PerformanceReview
    fun deletePerformanceReview(performanceReviewId : Long)
}