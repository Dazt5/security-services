package com.alejandra.security.services.adapters.outgoing.db.company.repository

import com.alejandra.security.services.adapters.outgoing.db.company.mapper.CompanyMapper
import com.alejandra.security.services.adapters.outgoing.db.exception.EntityNotFoundException
import com.alejandra.security.services.core.domain.company.model.Company
import com.alejandra.security.services.core.domain.company.port.CompanyRepositoryPort
import com.alejandra.security.services.core.domain.exception.BadRequestException
import org.springframework.stereotype.Repository

@Repository
class CompanyRepositoryPortImpl(private val jpaCompanyRepository: JpaCompanyRepository) : CompanyRepositoryPort {

    override fun getCompanies(): List<Company> {
        return jpaCompanyRepository.findAll().map { companyEntity -> CompanyMapper.toDomain(companyEntity) }
    }

    override fun getCompanyById(companyId: String): Company {
        try {
            return CompanyMapper.toDomain(jpaCompanyRepository.findById(companyId)
                    .orElseThrow { EntityNotFoundException("Company does not exists") })
        } catch (ex: EntityNotFoundException) {
            throw BadRequestException(ex.message, ex)
        }
    }

}