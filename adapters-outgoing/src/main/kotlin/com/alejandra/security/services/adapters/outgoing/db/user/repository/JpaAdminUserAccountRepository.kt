package com.alejandra.security.services.adapters.outgoing.db.user.repository

import com.alejandra.security.services.adapters.outgoing.db.user.entity.AdminUserAccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaAdminUserAccountRepository : JpaRepository<AdminUserAccountEntity, String> {

}