package com.github.vishalmadle13.HRMS.payloads

import com.github.vishalmadle13.HRMS.entites.Employee
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Data
data class DepartmentDto(
    var id: String = "",
    var department : String = "",
    var description : String = "",
  )