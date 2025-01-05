package com.alejandra.security.services.adapters.outgoing.db.company.repository

import com.alejandra.security.services.adapters.outgoing.db.company.entity.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaCompanyRepository : JpaRepository<CompanyEntity, String>