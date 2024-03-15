package com.github.vishalm1029.HRMS.exceptions

import lombok.Getter
import lombok.Setter
import org.springframework.security.core.AuthenticationException

@Setter
@Getter
class AuthenticationFailedException() : AuthenticationException("Bad credentials")