package com.github.vishalmadle13.HRMS.entites

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.annotation.Nullable
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
data class Position (
    @Id
    @Column(name = "id",  insertable = false, updatable = false, nullable = false)
    var id :String  = "",
    var title : String = "",
    var salary : Long = 0,
    var responsibility : String = "",
    @OneToMany(mappedBy = "position", cascade = [CascadeType.ALL] ) @JsonIgnore @JsonProperty("position")
    var employees : List<Employee>  = ArrayList()
)