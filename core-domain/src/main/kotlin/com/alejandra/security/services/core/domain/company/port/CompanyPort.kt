package com.alejandra.security.services.core.domain.company.port

import com.alejandra.security.services.core.domain.company.model.Company

interface CompanyPort {

    fun getCompanies(): List<Company>

    fun getCompanyById(companyId: String): Company

}
