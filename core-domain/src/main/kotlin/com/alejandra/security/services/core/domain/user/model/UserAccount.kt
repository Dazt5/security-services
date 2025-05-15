package com.alejandra.security.services.core.domain.user.model

import com.alejandra.security.services.core.domain.company.model.Company
import com.alejandra.security.services.core.domain.model.BaseTO

class UserAccount(
    var userAccountId: String,
    var role: Role,
    var name: String,
    var lastName: String,
    var email: String,
    var password: String?,
    var mfaEnabled: Boolean,
    var lastLogin: Long?,
    var status: Boolean,
    var isAdmin: Boolean,
    var company: Company?,
    updatedOn: Long?,
    updatedBy: String?,
    createdOn: Long,
    createdBy: String
) : BaseTO(updatedOn, updatedBy, createdOn, createdBy)