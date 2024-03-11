package com.github.vishalmadle13.HRMS.entites

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.annotation.Nullable
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Department(
    @Id
    @Column(name = "id",  insertable = false, updatable = false, nullable = false)
    var id : String = "",
    var department : String = "",
    var description : String = "",
    @OneToMany(mappedBy = "department", cascade = [CascadeType.ALL]) @JsonIgnoreProperties("employees")
    var employees : List<Employee> = ArrayList()
){
    override fun toString(): String {
        return "Department(id=$id, department='$department', description='$description')"
    }
}