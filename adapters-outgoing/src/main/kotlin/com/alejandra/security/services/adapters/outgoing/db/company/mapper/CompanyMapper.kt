package com.alejandra.security.services.adapters.outgoing.db.company.mapper

import com.alejandra.security.services.adapters.outgoing.db.company.entity.CompanyEntity
import com.alejandra.security.services.core.domain.company.model.Company

//TODO: Think if its worth to change this mapper for MapStruct Mappers
object CompanyMapper {

    fun toEntity(company: Company): CompanyEntity {
        return CompanyEntity(
            company.companyId,
            company.companyName,
            company.address,
            company.contactEmail,
            company.status,
            company.updatedOn,
            company.updatedBy,
            company.createdOn,
            company.createdBy
        )
    }

    fun toDomain(companyEntity: CompanyEntity): Company {
        return Company(
            companyEntity.companyId,
            companyEntity.companyName,
            companyEntity.address,
            companyEntity.contactEmail,
            companyEntity.status,
            companyEntity.updatedOn,
            companyEntity.updatedBy,
            companyEntity.createdOn,
            companyEntity.createdBy
        )
    }

}