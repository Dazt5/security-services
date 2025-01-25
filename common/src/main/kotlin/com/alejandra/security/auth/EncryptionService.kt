package com.alejandra.security.auth

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class EncryptionService(val passwordEncoder: PasswordEncoder) {

    fun encryptPassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    fun matchPassword(rawPassword: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }

}