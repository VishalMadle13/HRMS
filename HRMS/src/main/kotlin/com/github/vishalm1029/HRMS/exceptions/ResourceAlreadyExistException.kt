package com.github.vishalm1029.HRMS.exceptions

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ResourceAlreadyExistException(var resourceName: String) :
    RuntimeException(String.format("%s already exists", resourceName))
