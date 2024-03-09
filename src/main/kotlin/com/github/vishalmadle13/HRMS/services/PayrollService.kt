package com.github.vishalmadle13.HRMS.services

import com.github.vishalmadle13.HRMS.entites.Payroll
import com.github.vishalmadle13.HRMS.entites.Position
import jakarta.persistence.Id
import org.springframework.stereotype.Service

@Service
interface PayrollService {
    fun getAllPayrolls() : List<Payroll>
    fun getPayrollById(payrollId : Long) : Payroll?
    fun addPayroll(payroll: Payroll) : Payroll
    fun updatePayroll(payrollId : Long, payroll: Payroll) : Payroll?
    fun deletePayroll(payrollId : Long)
}