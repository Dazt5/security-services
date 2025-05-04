package com.alejandra.security.services.config

import com.alejandra.security.services.core.domain.exception.CustomHttpException
import com.alejandra.security.services.model.ResponseError
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(@Value("\${debug.isStackTraceEnabled: false}") private val isStackTraceEnabled: Boolean) {

    @ExceptionHandler(RuntimeException::class)
    fun handleGenericException(ex: RuntimeException): ResponseEntity<ResponseError> {
        return ResponseEntity.internalServerError()
            .body(
                ResponseError(
                    message = "Unexpected error",
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    stackTrace = getStackTrace(ex),
                    detailedMessage = ex.message
                )
            )
    }

    @ExceptionHandler(CustomHttpException::class)
    fun handleCustomHttpException(customHttpException: CustomHttpException): ResponseEntity<ResponseError> {
        return ResponseEntity(
            ResponseError(
                message = customHttpException.message,
                httpStatus = customHttpException.httpStatus.value(),
                stackTrace = getStackTrace(customHttpException),
                detailedMessage = getDetailedMessage(customHttpException)
            ), customHttpException.httpStatus
        )
    }

    private fun getStackTrace(ex: Throwable): String? {
        return if (isStackTraceEnabled) ExceptionUtils.getStackTrace(ex) else null
    }

    private fun getDetailedMessage(ex: Throwable): String? {
        return if (isStackTraceEnabled) ex.message else null
    }

}