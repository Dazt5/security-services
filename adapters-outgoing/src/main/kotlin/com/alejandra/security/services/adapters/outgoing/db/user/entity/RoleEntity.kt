package com.alejandra.security.services.adapters.outgoing.db.user.entity

import com.alejandra.security.services.adapters.outgoing.db.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "role")
class RoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var roleId: String,
    @Column(nullable = false)
    var roleCode: String,
    @Column(nullable = false)
    var roleName: String,
    @Column(nullable = false)
    var roleDescription: String,
    updatedOn: Long?,
    updatedBy: String?,
    createdOn: Long,
    createdBy: String
) : BaseEntity(updatedOn, updatedBy, createdOn, createdBy)