package com.alejandra.security.services.adapters.incoming.rest.company

import com.alejandra.security.services.core.domain.company.model.Company
import com.alejandra.security.services.core.domain.company.port.CompanyPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/company")
class CompanyController(private val companyService: CompanyPort) {

    @GetMapping
    fun getAll(): List<Company> {
        return companyService.getCompanies()
    }

    @GetMapping("/{companyId}")
    fun getById(@PathVariable companyId: String): Company {
        return companyService.getCompanyById(companyId)
    }

}