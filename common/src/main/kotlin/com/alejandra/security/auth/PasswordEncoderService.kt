package com.alejandra.security.auth

import com.alejandra.security.services.core.domain.common.PasswordEncoderPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

//TODO: Some unit tests need for this.
@Service
class PasswordEncoderService(val passwordEncoder: PasswordEncoder): PasswordEncoderPort {

    override fun encryptPassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    override fun matchPassword(rawPassword: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }

}