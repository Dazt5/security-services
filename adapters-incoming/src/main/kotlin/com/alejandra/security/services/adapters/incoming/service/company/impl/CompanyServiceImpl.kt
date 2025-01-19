package com.alejandra.security.services.adapters.incoming.service.company.impl

import com.alejandra.security.services.adapters.incoming.service.company.CompanyService
import com.alejandra.security.services.core.domain.company.model.Company
import com.alejandra.security.services.core.domain.company.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(private val companyRepository: CompanyRepository) : CompanyService {
    override fun getCompanies(): List<Company> {
        return companyRepository.getCompanies()
    }
}