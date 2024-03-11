package com.github.vishalmadle13.HRMS.entites

import lombok.*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class User(toString: String, s1: String, s2: String) {
    private var userId: String? = null
    private var name: String? = null
    private var email: String? = null
}