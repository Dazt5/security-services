package com.alejandra.security.services.core.domain.exception

import org.springframework.http.HttpStatus

abstract class CustomHttpException(
    override val message: String,
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override var cause: Throwable? = null
) : RuntimeException(message, cause)