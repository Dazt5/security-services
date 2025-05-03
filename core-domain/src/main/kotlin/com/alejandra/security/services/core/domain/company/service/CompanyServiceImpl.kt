package com.alejandra.security.services.core.domain.company.service

import com.alejandra.security.services.core.domain.company.model.Company
import com.alejandra.security.services.core.domain.company.port.CompanyPort
import com.alejandra.security.services.core.domain.company.port.CompanyRepositoryPort
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(private val companyRepositoryPort: CompanyRepositoryPort) : CompanyPort {
    override fun getCompanies(): List<Company> {
        return companyRepositoryPort.getCompanies()
    }

    override fun getCompanyById(companyId: String): Company {
        return companyRepositoryPort.getCompanyById(companyId)
    }

}