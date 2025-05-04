package com.alejandra.security.services.adapters.outgoing.db.user.repository

import com.alejandra.security.services.adapters.outgoing.db.user.entity.CompanyUserAccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaCompanyUserAccountRepository : JpaRepository<CompanyUserAccountEntity, String> {
}