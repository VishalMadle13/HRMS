package com.github.vishalm1029.HRMS.security


@JvmRecord
internal data class JwtResponse(val token: String, val username: String, val roles: List<String>)
