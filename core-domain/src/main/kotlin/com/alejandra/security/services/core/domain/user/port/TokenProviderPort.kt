package com.alejandra.security.services.core.domain.user.port

interface TokenProviderPort {

    //TODO: Improve this to receive UserAccount domain object instead of username.
    fun generateToken(username: String): String
    fun validateToken(token: String): String

}