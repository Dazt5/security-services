package com.alejandra.security.services.core.domain.model

open class BaseTO(
    open val updatedOn: Long?,
    open val updatedBy: String?,
    open val createdOn: Long,
    open val createdBy: String
)