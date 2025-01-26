package com.alejandra.security.services.core.domain.user.port

import com.alejandra.security.services.core.domain.user.model.UserAccount

interface UserAccountRepositoryPort {

    fun findByUsername(username: String): UserAccount

}