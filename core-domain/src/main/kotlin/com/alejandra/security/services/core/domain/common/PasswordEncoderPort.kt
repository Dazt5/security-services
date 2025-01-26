package com.alejandra.security.services.core.domain.common

interface PasswordEncoderPort {

    fun encryptPassword(rawPassword: String): String

    fun matchPassword(rawPassword: String, encodedPassword: String): Boolean

}