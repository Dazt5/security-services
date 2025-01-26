package com.alejandra.security.services.adapters.outgoing.db.user.entity

import com.alejandra.security.services.adapters.outgoing.db.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "user_account")
class UserAccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userAccountId: String,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    var role: RoleEntity,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var lastName: String,
    @Column(nullable = false)
    var email: String,
    var password: String?,
    @Column(nullable = false)
    var mfaEnabled: Boolean,
    var lastLogin: Long?,
    @Column(nullable = false)
    var status: Boolean,
    updatedOn: Long?,
    updatedBy: String?,
    createdOn: Long,
    createdBy: String
) : BaseEntity(updatedOn, updatedBy, createdOn, createdBy)