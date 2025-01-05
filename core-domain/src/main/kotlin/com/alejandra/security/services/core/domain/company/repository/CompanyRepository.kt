package com.alejandra.security.services.core.domain.company.repository

import com.alejandra.security.services.core.domain.company.model.Company

interface CompanyRepository {

    fun getCompanies(): List<Company>

}