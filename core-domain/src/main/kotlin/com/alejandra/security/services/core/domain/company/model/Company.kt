package com.alejandra.security.services.core.domain.company.model

import com.alejandra.security.services.core.domain.model.BaseTO

class Company(
    val companyId: String,
    val companyName: String,
    val address: String,
    val contactEmail: String,
    val status: Boolean,
    updatedOn: Long?,
    updatedBy: String?,
    createdOn: Long,
    createdBy: String
) : BaseTO(updatedOn, updatedBy, createdOn, createdBy)