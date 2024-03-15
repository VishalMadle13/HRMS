package com.github.vishalm1029.HRMS.services

import com.github.vishalm1029.HRMS.payloads.PayrollDto
import org.springframework.stereotype.Service

@Service
interface PayrollService {
    fun getAllPayrolls(): List<PayrollDto>
    fun getPayrollById(payrollId: Long): PayrollDto?
    fun addPayroll(payrollDto: PayrollDto): PayrollDto
    fun updatePayroll(payrollId: Long, payrollDto: PayrollDto): PayrollDto?
    fun deletePayroll(payrollId: Long)
}