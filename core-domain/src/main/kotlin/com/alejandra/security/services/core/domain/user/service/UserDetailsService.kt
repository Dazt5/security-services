package com.alejandra.security.services.core.domain.user.service

import com.alejandra.security.services.core.domain.user.model.UserAccount
import com.alejandra.security.services.core.domain.user.port.UserAccountRepositoryPort
import com.alejandra.security.services.core.domain.user.port.UserDetailsPort
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val userAccountRepositoryPort: UserAccountRepositoryPort
) : UserDetailsPort {

    override fun getUserDetails(username: String): UserAccount {
        return userAccountRepositoryPort.findByUsername(username)
    }

}