package com.alejandra.security.services.adapters.incoming.company.rest.company

import com.alejandra.security.services.adapters.incoming.company.service.company.CompanyService
import com.alejandra.security.services.core.domain.company.model.Company
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/company")
class CompanyController(private val companyService: CompanyService) {

    @GetMapping
    fun getCompany(): List<Company> {
        return companyService.getCompanies()
    }


}