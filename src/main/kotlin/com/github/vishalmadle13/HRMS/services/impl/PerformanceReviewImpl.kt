package com.github.vishalmadle13.HRMS.services.impl

import com.github.vishalmadle13.HRMS.entites.PerformanceReview
import com.github.vishalmadle13.HRMS.repositories.PerformanceReviewRepo
import org.springframework.beans.factory.annotation.Autowired

class PerformanceReviewImpl (@Autowired val performanceReviewRepo: PerformanceReviewRepo) {
    fun getAllPerformanceReview() : List<PerformanceReview> =  performanceReviewRepo.findAll()
    fun getPerformanceReviewById(performanceReviewId : Long)  : PerformanceReview? = performanceReviewRepo.findById(performanceReviewId).orElse(null)
    fun addPerformanceReview(performanceReview: PerformanceReview) : PerformanceReview = performanceReviewRepo.save(performanceReview)
    fun updatePerformanceReview(performanceReviewId: Long, performanceReview: PerformanceReview) : PerformanceReview = performanceReviewRepo.save(performanceReview)
    fun deletePerformanceReview(performanceReviewId : Long) = performanceReviewRepo.deleteById(performanceReviewId)
}