package com.alejandra.security.services.adapters.outgoing.db.user.mapper

import com.alejandra.security.services.adapters.outgoing.db.user.entity.RoleEntity
import com.alejandra.security.services.core.domain.user.model.Role

object RoleMapper {

    fun toEntity(role: Role): RoleEntity {
        return RoleEntity(
            role.roleId,
            role.roleCode,
            role.roleName,
            role.roleDescription,
            role.updatedOn,
            role.updatedBy,
            role.createdOn,
            role.createdBy
        )
    }

    fun toDomain(roleEntity: RoleEntity): Role {
        return Role(
            roleEntity.roleId,
            roleEntity.roleCode,
            roleEntity.roleName,
            roleEntity.roleDescription,
            roleEntity.updatedOn,
            roleEntity.updatedBy,
            roleEntity.createdOn,
            roleEntity.createdBy
        )
    }

}