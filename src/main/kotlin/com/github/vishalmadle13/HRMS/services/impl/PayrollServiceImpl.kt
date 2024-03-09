package com.github.vishalmadle13.HRMS.services.impl

import com.github.vishalmadle13.HRMS.entites.Payroll
import com.github.vishalmadle13.HRMS.repositories.PayrollRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class PayrollServiceImpl ( @Autowired val payrollRepo: PayrollRepo) {
    fun getAllPayrolls() : List<Payroll> = payrollRepo.findAll()
    fun getPayrollById(payrollId : Long) : Payroll? = payrollRepo.findByIdOrNull(payrollId)
    fun addPayroll(payroll: Payroll) : Payroll = payrollRepo.save(payroll)
    fun updatePayroll(payrollId : Long, payroll: Payroll) : Payroll? = payrollRepo.save(payroll)
    fun deletePayroll(payrollId : Long) = payrollRepo.deleteById(payrollId)
}