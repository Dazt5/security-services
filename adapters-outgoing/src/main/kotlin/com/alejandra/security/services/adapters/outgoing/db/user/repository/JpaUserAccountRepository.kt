package com.alejandra.security.services.adapters.outgoing.db.user.repository

import com.alejandra.security.services.adapters.outgoing.db.user.entity.UserAccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserAccountRepository : JpaRepository<UserAccountEntity, String> {

    fun findByEmail(email: String): UserAccountEntity?

}