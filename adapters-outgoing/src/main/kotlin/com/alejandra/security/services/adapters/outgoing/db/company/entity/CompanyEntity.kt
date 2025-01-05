package com.alejandra.security.services.adapters.outgoing.db.company.entity

import com.alejandra.security.services.adapters.outgoing.db.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "company")
class CompanyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var companyId: String,
    @Column(nullable = false)
    var companyName: String,
    @Column(nullable = false)
    var address: String,
    @Column(nullable = false)
    var contactEmail: String,
    @Column(nullable = false)
    var status: Boolean,
    updatedOn: Long?,
    updatedBy: String?,
    createdOn: Long,
    createdBy: String
) : BaseEntity(updatedOn, updatedBy, createdOn, createdBy)