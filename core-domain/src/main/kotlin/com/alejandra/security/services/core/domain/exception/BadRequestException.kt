package com.alejandra.security.services.core.domain.exception

import org.springframework.http.HttpStatus

class BadRequestException(override val message: String, override var cause: Throwable? = null) :
    CustomHttpException(message, HttpStatus.BAD_REQUEST, cause)