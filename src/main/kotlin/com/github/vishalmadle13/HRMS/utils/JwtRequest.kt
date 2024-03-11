package com.github.vishalmadle13.HRMS.utils

import lombok.*

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

class JwtRequest {
     val email: String? = null
     val password: String? = null
}