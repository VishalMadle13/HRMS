package com.github.vishalmadle13.HRMS.services.impl

import com.github.vishalmadle13.HRMS.entites.Employee
import com.github.vishalmadle13.HRMS.entites.PerformanceReview
import com.github.vishalmadle13.HRMS.exceptions.ResourceNotFoundException
import com.github.vishalmadle13.HRMS.payloads.EmployeeDto
import com.github.vishalmadle13.HRMS.payloads.PerformanceReviewDto
import com.github.vishalmadle13.HRMS.repositories.EmployeeRepo
import com.github.vishalmadle13.HRMS.repositories.PerformanceReviewRepo
import com.github.vishalmadle13.HRMS.services.PerformanceReviewService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class PerformanceReviewServiceImpl (
    @Autowired val performanceReviewRepo: PerformanceReviewRepo,
    @Autowired val employeeRepo: EmployeeRepo,
    @Autowired val mapper: ModelMapper,
)  : PerformanceReviewService {

    override fun getAllPerformanceReview(): List<PerformanceReviewDto> {
        return performanceReviewRepo
            .findAll()
                .stream()
                    .map {
                        performanceReview -> toDto(performanceReview)
                    }
                    .collect(Collectors.toList())
    }

    override fun getPerformanceReviewById(performanceReviewId: Long): PerformanceReviewDto? {
        val performanceReview: PerformanceReview = performanceReviewRepo
            .findById(performanceReviewId)
                .orElseThrow {
                    ResourceNotFoundException("PerformanceReview", "Id", performanceReviewId)
                }
        return toDto(performanceReview)
    }
    override fun getPerformanceReviewByEmployeeName(employeeName:Long):List<PerformanceReviewDto>{
        var performanceReviews :List<PerformanceReview>  = performanceReviewRepo.findByEmployeeName(employeeName=employeeName)
        return performanceReviews.stream().map {
            performanceReview-> toDto(performanceReview)
        }
        .collect(Collectors.toList())
    }

    override fun addPerformanceReview(performanceReviewDto: PerformanceReviewDto): PerformanceReviewDto {
        var performanceReview: PerformanceReview = dtoToEntity(performanceReviewDto)
        val employee: Employee = employeeRepo
            .findById(performanceReview.employee.id!!)
                .orElseThrow {
                    ResourceNotFoundException("Employee", "Id", performanceReview.employee.id)
                }
        performanceReview.employee = employee

        val savedPerformanceReview: PerformanceReview = performanceReviewRepo.save(performanceReview)
        return toDto(savedPerformanceReview)
    }

    override fun updatePerformanceReview(performanceReviewId: Long, performanceReviewDto: PerformanceReviewDto): PerformanceReviewDto {
        performanceReviewRepo
            .findById(performanceReviewId)
                .orElseThrow {
                    ResourceNotFoundException("PerformanceReview", "Id", performanceReviewId)
                }
        val employee: Employee = employeeRepo
            .findById(performanceReviewDto.employeeId!!)
                .orElseThrow {
                    ResourceNotFoundException("Employee", "Id", performanceReviewDto.employeeId)
                }
        var performanceReview: PerformanceReview = dtoToEntity(performanceReviewDto)
        performanceReview.employee = employee
        performanceReview.id = performanceReviewId
        val updatedPerformanceReview: PerformanceReview = performanceReviewRepo.save(performanceReview)
        return toDto(updatedPerformanceReview)

    }

    override fun deletePerformanceReview(performanceReviewId: Long) {
        performanceReviewRepo
            .findById(performanceReviewId)
                .orElseThrow {
                    ResourceNotFoundException("PerformanceReview", "Id", performanceReviewId)
                }
        return performanceReviewRepo.deleteById(performanceReviewId)
    }

    private fun toDto(performanceReview: PerformanceReview): PerformanceReviewDto {
        var performanceReviewDto : PerformanceReviewDto = PerformanceReviewDto()
        performanceReviewDto.id= performanceReview.id
        performanceReviewDto.reviewDate= performanceReview.reviewDate
        performanceReviewDto.comments  = performanceReview.comments
        performanceReviewDto.rating    = performanceReview.rating
        performanceReviewDto.employeeId= performanceReview.employee.id
        performanceReviewDto.employeeFirstName = performanceReview.employee.firstName
        performanceReviewDto.employeeLastName  = performanceReview.employee.lastName
        return performanceReviewDto
    }
    private fun dtoToEntity(performanceReviewDto: PerformanceReviewDto) : PerformanceReview {
        var performanceReview : PerformanceReview = PerformanceReview()
        performanceReview.id = performanceReviewDto.id
        performanceReview.reviewDate = performanceReviewDto.reviewDate
        performanceReview.comments = performanceReviewDto.comments
        performanceReview.rating   = performanceReviewDto.rating
        performanceReview.employee = employeeRepo.findById(performanceReviewDto.employeeId!!).orElseThrow {
            ResourceNotFoundException("Employee","Id",performanceReviewDto.employeeId)
        }
        return performanceReview
    }

}