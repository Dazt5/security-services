package com.alejandra.security.services.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseError(val message: String, val stackTrace: String? = null)