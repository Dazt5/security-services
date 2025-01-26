package com.alejandra.security.services.core.domain.user.model

import com.alejandra.security.services.core.domain.model.BaseTO

class Role(
    var roleId: String,
    var roleCode: String,
    var roleName: String,
    var roleDescription: String,
    updatedOn: Long?,
    updatedBy: String?,
    createdOn: Long,
    createdBy: String
) : BaseTO(updatedOn, updatedBy, createdOn, createdBy)