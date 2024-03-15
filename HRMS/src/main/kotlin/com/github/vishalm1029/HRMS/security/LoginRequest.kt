package com.github.vishalm1029.HRMS.security

import jakarta.validation.constraints.NotBlank


@JvmRecord
internal data class LoginRequest(val username: @NotBlank String?, val password: @NotBlank String?)
