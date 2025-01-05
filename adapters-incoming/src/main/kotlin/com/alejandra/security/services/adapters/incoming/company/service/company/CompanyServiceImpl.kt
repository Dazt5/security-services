package com.alejandra.security.services.adapters.incoming.company.service.company

import com.alejandra.security.services.core.domain.company.model.Company
import com.alejandra.security.services.core.domain.company.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(private val companyRepository: CompanyRepository) : CompanyService {
    override fun getCompanies(): List<Company> {
        return companyRepository.getCompanies()
    }
}