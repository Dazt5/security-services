package com.alejandra.security.services.adapters.outgoing.db.user.mapper

import com.alejandra.security.services.adapters.outgoing.db.company.entity.CompanyEntity
import com.alejandra.security.services.adapters.outgoing.db.company.mapper.CompanyMapper
import com.alejandra.security.services.adapters.outgoing.db.user.entity.UserAccountEntity
import com.alejandra.security.services.core.domain.exception.UnauthorizedException
import com.alejandra.security.services.core.domain.user.model.UserAccount

object UserAccountMapper {

    fun toEntity(userAccount: UserAccount): UserAccountEntity {
        return UserAccountEntity(
            userAccount.userAccountId,
            RoleMapper.toEntity(userAccount.role),
            userAccount.name,
            userAccount.lastName,
            userAccount.email,
            userAccount.password,
            userAccount.mfaEnabled,
            userAccount.lastLogin,
            userAccount.status,
            null,
            null,
            userAccount.updatedOn,
            userAccount.updatedBy,
            userAccount.createdOn,
            userAccount.createdBy
        )
    }

    fun toDomain(userAccountEntity: UserAccountEntity): UserAccount {
        val isAdmin = userAccountEntity.adminUser?.let { true } ?: userAccountEntity.companyUser?.let { false }
        ?: throw UnauthorizedException("Your user has no access to our system, please check it calling support")
        var company: CompanyEntity? = null
        if (!isAdmin){
            company = userAccountEntity.companyUser!!.company
        }
        return UserAccount(
            userAccountEntity.userAccountId,
            RoleMapper.toDomain(userAccountEntity.role),
            userAccountEntity.name,
            userAccountEntity.lastName,
            userAccountEntity.email,
            userAccountEntity.password,
            userAccountEntity.mfaEnabled,
            userAccountEntity.lastLogin,
            userAccountEntity.status,
            isAdmin,
            if (company != null) CompanyMapper.toDomain(company) else null,
            userAccountEntity.updatedOn,
            userAccountEntity.updatedBy,
            userAccountEntity.createdOn,
            userAccountEntity.createdBy,
        )
    }

}