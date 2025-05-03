package com.alejandra.security.services.config

import com.alejandra.security.services.core.domain.exception.CustomHttpException
import com.alejandra.security.services.model.ResponseError
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(@Value("\${debug.isStackTraceEnabled: false}") private val isStackTraceEnabled: Boolean) {

    @ExceptionHandler(CustomHttpException::class)
    fun handleCustomHttpException(customHttpException: CustomHttpException): ResponseEntity<ResponseError> {

        return ResponseEntity(
            ResponseError(customHttpException.message,
                if (isStackTraceEnabled) ExceptionUtils.getStackTrace(customHttpException) else null
            ), customHttpException.httpStatus
        )
    }

}