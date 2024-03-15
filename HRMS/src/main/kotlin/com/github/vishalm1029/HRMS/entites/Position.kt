package com.github.vishalm1029.HRMS.entites

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Position(
    @Id
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    var id: String = "",
    var title: String = "",
    var salary: Long = 0,
    var responsibility: String = "",
    @OneToMany(mappedBy = "position", cascade = [CascadeType.ALL]) @JsonIgnore @JsonProperty("position")
    var employees: List<Employee> = ArrayList()
)