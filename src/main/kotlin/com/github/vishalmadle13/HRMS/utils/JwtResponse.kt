package com.github.vishalmadle13.HRMS.utils

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import lombok.ToString


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class JwtResponse {
     var jwtToken: String? = null
     var username: String? = null
}