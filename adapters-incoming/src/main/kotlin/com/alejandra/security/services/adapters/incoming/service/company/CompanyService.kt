package com.alejandra.security.services.adapters.incoming.service.company

import com.alejandra.security.services.core.domain.company.model.Company

interface CompanyService {

    fun getCompanies(): List<Company>

}