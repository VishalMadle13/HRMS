package com.github.vishalm1029.HRMS.services.impl

import com.github.vishalm1029.HRMS.entites.Employee
import com.github.vishalm1029.HRMS.entites.Payroll
import com.github.vishalm1029.HRMS.exceptions.ResourceAlreadyExistException
import com.github.vishalm1029.HRMS.exceptions.ResourceNotFoundException
import com.github.vishalm1029.HRMS.payloads.PayrollDto
import com.github.vishalm1029.HRMS.repositories.EmployeeRepo
import com.github.vishalm1029.HRMS.repositories.PayrollRepo
import com.github.vishalm1029.HRMS.services.PayrollService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class PayrollServiceImpl(
    @Autowired val payrollRepo: PayrollRepo,
    @Autowired val mapper: ModelMapper,
    @Autowired val employeeRepo: EmployeeRepo
) : PayrollService {
    override fun getAllPayrolls(): List<PayrollDto> {
        return payrollRepo
            .findAll()
            .stream()
            .map { payroll: Payroll ->
                this.mapper.map(payroll, PayrollDto::class.java)
            }
            .collect(Collectors.toList())
    }

    override fun getPayrollById(payrollId: Long): PayrollDto? {
        val payrollEntity: Payroll = payrollRepo
            .findById(payrollId)
            .orElseThrow {
                ResourceNotFoundException("Payroll", "Id", payrollId)
            }
        return mapper.map(
            payrollEntity, PayrollDto::class.java
        )
    }

    override fun addPayroll(payrollDto: PayrollDto): PayrollDto {
        val employee: Employee = employeeRepo
            .findById(payrollDto.employee.id!!)
            .orElseThrow {
                ResourceNotFoundException("Employee", "Id", payrollDto.employee.id)
            }
        if (payrollDto.id != null) {
            if (payrollRepo.findById(payrollDto.id!!).isPresent) throw ResourceAlreadyExistException("Position")
        }
        println(employee.toString())
        var payroll: Payroll = mapper.map(payrollDto, Payroll::class.java)
        payroll.employee = employee
        println(payroll.toString())
        var savedPayroll: PayrollDto = mapper.map(
            payrollRepo.save(payroll), PayrollDto::class.java
        )
        println(savedPayroll.toString())
        return savedPayroll
    }

    override fun updatePayroll(payrollId: Long, payrollDto: PayrollDto): PayrollDto {
        payrollRepo
            .findById(payrollId)
            .orElseThrow {
                ResourceNotFoundException("Payroll", "Id", payrollId)
            }
        val employee: Employee = employeeRepo
            .findById(payrollDto.employee.id!!)
            .orElseThrow {
                ResourceNotFoundException("Employee", "Id", payrollDto.employee.id)
            }

        val updatedPayroll: Payroll = mapper.map(payrollDto, Payroll::class.java)
        updatedPayroll.id = payrollId
        updatedPayroll.employee = employee

        return mapper.map(
            payrollRepo.save(updatedPayroll), PayrollDto::class.java
        )
    }

    override fun deletePayroll(payrollId: Long) {
        payrollRepo
            .findById(payrollId)
            .orElseThrow {
                ResourceNotFoundException("Payroll", "Id", payrollId)
            }
        payrollRepo.deleteById(payrollId)
    }
}