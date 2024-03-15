package com.github.vishalm1029.HRMS.controller

import com.github.vishalm1029.HRMS.payloads.PayrollDto
import com.github.vishalm1029.HRMS.services.PayrollService
import com.github.vishalm1029.HRMS.utils.ApiResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payroll")
class PayrollController(
    @Autowired val payrollService: PayrollService
) {
    // ----------------------------- Get All Payroll ----------------------------
    @GetMapping("/")
    fun getAllPayrolls(): ResponseEntity<List<PayrollDto>> {
        return ResponseEntity(
            this.payrollService.getAllPayrolls(), HttpStatus.OK
        )
    }

    // -------------------------------- Get Payroll By Id -------------------------------
    @GetMapping("/{payrollId}")
    fun getPayrollById(@PathVariable("payrollId") payrollId: Long): ResponseEntity<PayrollDto> {
        return ResponseEntity(
            this.payrollService.getPayrollById(payrollId), HttpStatus.OK
        )
    }

    // -------------------------------- Add Payroll --------------------------------
    @PostMapping("/")
    fun addPayroll(@RequestBody @Valid payrollDto: PayrollDto): ResponseEntity<PayrollDto> {
        return ResponseEntity(
            this.payrollService.addPayroll(payrollDto), HttpStatus.CREATED
        )
    }

    // -------------------------------- Update Payroll --------------------------------
    @PutMapping("/{payrollId}")
    fun updatePayroll(
        @PathVariable("payrollId") payrollId: Long,
        @Valid @RequestBody payrollDto: PayrollDto
    ): ResponseEntity<PayrollDto> {
        if (payrollId != payrollDto.id) {
            var failResponse: MultiValueMap<String, String> = LinkedMultiValueMap()
            failResponse.add("message", "Request-Body and Path-Variable is NOT Equal")
            failResponse.add("success", "false")
            return ResponseEntity(PayrollDto(), failResponse, HttpStatus.NOT_MODIFIED)
        }
        return ResponseEntity(
            this.payrollService.updatePayroll(payrollId, payrollDto), HttpStatus.OK
        )
    }

    // -------------------------------- Delete Payroll --------------------------------
    @DeleteMapping("/{payrollId}")
    fun deletePayrollById(@PathVariable("payrollId") payrollId: Long): ResponseEntity<ApiResponse> {
        this.payrollService.deletePayroll(payrollId)
        return ResponseEntity(
            ApiResponse("Payroll Deleted Successfully", true), HttpStatus.OK
        )
    }
}

