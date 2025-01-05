package com.alejandra.security.services.adapters.outgoing.db.company.repository

import com.alejandra.security.services.adapters.outgoing.db.company.mapper.CompanyMapper
import com.alejandra.security.services.core.domain.company.model.Company
import com.alejandra.security.services.core.domain.company.repository.CompanyRepository
import org.springframework.stereotype.Repository

@Repository
class CompanyRepositoryImpl(private val jpaCompanyRepository: JpaCompanyRepository) : CompanyRepository {

    override fun getCompanies(): List<Company> {
        return jpaCompanyRepository.findAll().map { companyEntity -> CompanyMapper.toDomain(companyEntity) }
    }
}