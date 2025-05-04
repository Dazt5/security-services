package com.alejandra.security.services.config.authManager

import com.alejandra.security.auth.UserAccountDetails
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import java.util.function.Supplier

class AdminAuthenticationManager : AuthorizationManager<RequestAuthorizationContext> {

    override fun check(
        authentication: Supplier<Authentication>,
        context: RequestAuthorizationContext?
    ): AuthorizationDecision {
        val authentication = authentication.get()
        val principal = authentication.principal
        val isAdmin = (principal as? UserAccountDetails)?.isAdmin()
        return AuthorizationDecision(isAdmin == true)
    }
}