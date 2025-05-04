package com.alejandra.security.auth

import com.alejandra.security.services.core.domain.user.model.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

//TODO: Some unit tests need for this.
class UserAccountDetails(
    private val username: String,
    private val password: String?,
    private val authorities: List<Role>,
    private val mfaEnabled: Boolean,
    private val isEnabled: Boolean,
    private val isAdmin: Boolean
) : UserDetails {

    override fun getAuthorities(): Collection<SimpleGrantedAuthority> {
        return authorities.map { SimpleGrantedAuthority(it.roleId) }
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isEnabled(): Boolean {
        return isEnabled
    }

    fun isMfaEnabled(): Boolean {
        return this.mfaEnabled
    }

    fun isAdmin(): Boolean {
        return this.isAdmin
    }

}