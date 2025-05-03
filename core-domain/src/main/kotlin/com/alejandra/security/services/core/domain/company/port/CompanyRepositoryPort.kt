package com.alejandra.security.services.core.domain.company.port

import com.alejandra.security.services.core.domain.company.model.Company

interface CompanyRepositoryPort {

    fun getCompanies(): List<Company>

    fun getCompanyById(companyId: String): Company

}