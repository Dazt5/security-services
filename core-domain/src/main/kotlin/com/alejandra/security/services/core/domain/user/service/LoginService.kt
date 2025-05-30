package com.alejandra.security.services.core.domain.user.service

import com.alejandra.security.services.core.domain.exception.UnauthorizedException
import com.alejandra.security.services.core.domain.common.PasswordEncoderPort
import com.alejandra.security.services.core.domain.user.model.UserAccount
import com.alejandra.security.services.core.domain.user.port.LoginPort
import com.alejandra.security.services.core.domain.user.port.TokenProviderPort
import com.alejandra.security.services.core.domain.user.port.UserAccountRepositoryPort
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userAccountRepositoryPort: UserAccountRepositoryPort,
    private val tokenProviderPort: TokenProviderPort,
    private val passwordEncoderPort: PasswordEncoderPort
) : LoginPort {


    override fun login(username: String, password: String): String {
        val userAccount: UserAccount = this.userAccountRepositoryPort.findByUsername(username)
        userAccount.password?.let {
            if (!passwordEncoderPort.matchPassword(password, it)) {
                throw UnauthorizedException("User or password does not match, try again...")
            }
        } ?: run {
            throw UnauthorizedException("Your User has problem, call support for more information.")
        }
        return this.tokenProviderPort.generateToken(username)
    }
}