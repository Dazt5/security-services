package com.alejandra.security.services.adapters.outgoing.db.user.entity

import com.alejandra.security.services.adapters.outgoing.db.entity.CreationAuditableEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "company_user_account")
class CompanyUserAccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var companyUserAccountId: String,
    @OneToOne
    @JoinColumn(name = "user_account_id", nullable = false, unique = true)
    var userAccount: UserAccountEntity,
    createdOn: Long,
    createdBy: String
) : CreationAuditableEntity(createdOn, createdBy)