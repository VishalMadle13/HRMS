package com.github.vishalmadle13.HRMS.exceptions

import lombok.Getter
import lombok.Setter
import java.util.Objects


@Getter
@Setter
class ResourceNotFoundException  (var resourceName: String?, var fieldName: String?, var fieldValue: Any? ) :
    RuntimeException(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue?.toString() ?: "NULL"))
