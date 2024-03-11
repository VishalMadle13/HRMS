package com.github.vishalmadle13.HRMS.services

 import com.github.vishalmadle13.HRMS.payloads.PerformanceReviewDto
 import org.springframework.stereotype.Service

@Service
interface PerformanceReviewService {
    fun getAllPerformanceReview() : List<PerformanceReviewDto>
    fun getPerformanceReviewById(performanceReviewId : Long)  : PerformanceReviewDto ?
    fun getPerformanceReviewByEmployeeName(employeeName: Long) : List<PerformanceReviewDto>
    fun addPerformanceReview(performanceReviewDto: PerformanceReviewDto) : PerformanceReviewDto
    fun updatePerformanceReview(performanceReviewId: Long, performanceReviewDto: PerformanceReviewDto) : PerformanceReviewDto
    fun deletePerformanceReview(performanceReviewId : Long)
}