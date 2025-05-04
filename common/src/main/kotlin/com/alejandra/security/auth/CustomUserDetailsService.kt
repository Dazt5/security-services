package com.alejandra.security.auth

import com.alejandra.security.services.core.domain.user.port.UserAccountRepositoryPort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


//TODO: Some unit tests need for this.
@Service
class CustomUserDetailsService(
    private val userAccountRepositoryPort: UserAccountRepositoryPort
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userAccountRepositoryPort.findByUsername(username)
        return UserAccountDetails(user.email, user.password, listOf(user.role), user.mfaEnabled, user.status, user.isAdmin)
    }

}