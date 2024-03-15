package com.github.vishalm1029.HRMS.entites

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Department(
    @Id
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    var id: String = "",
    var department: String = "",
    var description: String = "",
    @OneToMany(mappedBy = "department", cascade = [CascadeType.ALL]) @JsonIgnoreProperties("employees")
    var employees: List<Employee> = ArrayList()
) {
    override fun toString(): String {
        return "Department(id=$id, department='$department', description='$description')"
    }
}