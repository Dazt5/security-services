package com.alejandra.security.services.core.domain.exception

import org.springframework.http.HttpStatus

class UnauthorizedException(override val message: String, override var cause: Throwable? = null) :
    CustomHttpException(message, HttpStatus.UNAUTHORIZED, cause)