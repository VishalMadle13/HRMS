package com.github.vishalmadle13.HRMS.controller

import com.github.vishalmadle13.HRMS.payloads.PerformanceReviewDto
import com.github.vishalmadle13.HRMS.services.PerformanceReviewService
 import com.github.vishalmadle13.HRMS.utils.ApiResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/performance-review")
class PerformanceReviewController (
    @Autowired val performanceReviewService: PerformanceReviewService
){
    // ----------------------------- Get All PerformanceReview ----------------------------
    @GetMapping("/")
    fun getAllPerformanceReviews(): ResponseEntity<List<PerformanceReviewDto>> {
        return ResponseEntity(
                this.performanceReviewService.getAllPerformanceReview(),HttpStatus.OK
        )
    }

    // -------------------------------- Get PerformanceReview By Id -------------------------------
    @GetMapping("/{performanceReviewId}")
    fun getPerformanceReviewById(@PathVariable("performanceReviewId") performanceReviewId: Long): ResponseEntity<PerformanceReviewDto> {
        return ResponseEntity(
            this.performanceReviewService.getPerformanceReviewById(performanceReviewId), HttpStatus.OK
        )
    }

    // -------------------------------- Get PerformanceReview By Employee Name -------------------------------
    @GetMapping("?employee-name={employeeName}")
    fun getPerformanceReviewByEmployeeName(@PathVariable("employeeName") employeeName: Long): ResponseEntity<List<PerformanceReviewDto>> {
        return ResponseEntity(
            this.performanceReviewService.getPerformanceReviewByEmployeeName(employeeName), HttpStatus.OK
        )
    }

    // -------------------------------- Add PerformanceReview --------------------------------
    @PostMapping("/")
    fun addPerformanceReview( @RequestBody @Valid performanceReviewDto: PerformanceReviewDto): ResponseEntity<PerformanceReviewDto> {
        return ResponseEntity(
            this.performanceReviewService.addPerformanceReview(performanceReviewDto), HttpStatus.CREATED
        )
    }

    // -------------------------------- Update PerformanceReview --------------------------------
    @PutMapping("/{performanceReviewId}")
    fun updatePerformanceReview(@PathVariable("performanceReviewId") performanceReviewId: Long, @Valid @RequestBody performanceReviewDto: PerformanceReviewDto): ResponseEntity<PerformanceReviewDto> {
        if(performanceReviewId != performanceReviewDto.id) {
            var failResponse : MultiValueMap<String,String> = LinkedMultiValueMap()
            failResponse.add("message","Request-Body and Path-Variable is NOT Equal")
            failResponse.add("success","false")
            return ResponseEntity(PerformanceReviewDto(),failResponse ,HttpStatus.NOT_MODIFIED)
        }
        return ResponseEntity(
            this.performanceReviewService.updatePerformanceReview(performanceReviewId, performanceReviewDto), HttpStatus.OK
        )
    }

    // -------------------------------- Delete PerformanceReview --------------------------------
    @DeleteMapping("/{performanceReviewId}")
    fun deletePerformanceReviewById(@PathVariable("performanceReviewId") performanceReviewId: Long) : ResponseEntity<ApiResponse> {
        this.performanceReviewService.deletePerformanceReview(performanceReviewId)
        return ResponseEntity(
            ApiResponse("PerformanceReview Deleted Successfully",true), HttpStatus.OK
        )
    }
}

