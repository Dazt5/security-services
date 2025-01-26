package com.alejandra.security.services.core.domain.user.port

interface LoginPort {

    fun login(username: String, password: String): String

}