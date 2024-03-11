package com.github.vishalmadle13.HRMS.services.impl

import com.github.vishalmadle13.HRMS.entites.Employee
import com.github.vishalmadle13.HRMS.entites.Payroll
import com.github.vishalmadle13.HRMS.entites.PerformanceReview
import com.github.vishalmadle13.HRMS.exceptions.ResourceNotFoundException
import com.github.vishalmadle13.HRMS.payloads.PayrollDto
import com.github.vishalmadle13.HRMS.repositories.EmployeeRepo
import com.github.vishalmadle13.HRMS.repositories.PayrollRepo
import com.github.vishalmadle13.HRMS.services.PayrollService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class PayrollServiceImpl (
    @Autowired val payrollRepo: PayrollRepo,
    @Autowired val mapper: ModelMapper,
    @Autowired val employeeRepo: EmployeeRepo
)  : PayrollService{
    override fun getAllPayrolls() : List<PayrollDto>  {
        return payrollRepo
            .findAll()
                .stream()
                    .map {
                        payroll ->  this.mapper.map(payroll, PayrollDto::class.java )
                    }
                    .collect(Collectors.toList())
    }
    override fun getPayrollById(payrollId : Long) : PayrollDto? {
        val payrollEntity : Payroll = payrollRepo
            .findById(payrollId)
                .orElseThrow{
                    ResourceNotFoundException("Payroll","Id",payrollId )
                }
        return mapper.map(
            payrollEntity, PayrollDto::class.java
        )
    }

    override fun addPayroll(payrollDto : PayrollDto) : PayrollDto {
        var payroll: Payroll = mapper.map(payrollDto, Payroll::class.java)
        val employee : Employee = employeeRepo
            .findById(payroll.employee.id!!)
                .orElseThrow{
                    ResourceNotFoundException("Employee","Id",payroll.employee.id)
                }
        payroll.employee = employee
        return mapper.map(
            payrollRepo.save(payroll), PayrollDto::class.java
        )
    }

    override fun updatePayroll(payrollId : Long, payrollDto : PayrollDto) : PayrollDto {
        payrollRepo
            .findById(payrollId)
                .orElseThrow{
                    ResourceNotFoundException("Payroll","Id",payrollId )
                }
        val employee : Employee = employeeRepo
            .findById(payrollDto.employee.id!!)
                .orElseThrow{
                    ResourceNotFoundException("Employee","Id",payrollDto.employee.id)
                }

        val updatedPayroll: Payroll = mapper.map(payrollDto, Payroll::class.java)
        updatedPayroll.id = payrollId
        updatedPayroll.employee = employee

        return mapper.map(
            payrollRepo.save(updatedPayroll), PayrollDto::class.java
        )
    }

    override fun deletePayroll(payrollId: Long)  {
        payrollRepo
            .findById(payrollId)
                .orElseThrow{
                    ResourceNotFoundException("Payroll", "Id", payrollId )
                }
        payrollRepo.deleteById(payrollId)
    }

}