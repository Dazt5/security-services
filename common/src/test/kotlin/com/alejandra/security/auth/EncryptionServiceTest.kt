package com.alejandra.security.auth

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


class EncryptionServiceTest {

    private lateinit var passwordEncoder: PasswordEncoder

    private lateinit var encryptionService: EncryptionService

    @BeforeEach
    fun setUp() {
        passwordEncoder = BCryptPasswordEncoder()
        encryptionService = EncryptionService(passwordEncoder)
    }

    @Test
    fun `should encrypt password successfully`() {
        val password = "myPassword"
        val passwordEncoded: String = encryptionService.encryptPassword(password)
        println(passwordEncoded)
        Assertions.assertNotNull(passwordEncoder)
    }

    @Test
    fun `should return true when checking passwords because both are same`() {
        val rawPassword = "myPassword"
        val encryptedPassword = "\$2a\$10\$fkgj1G42epW6RzHwdy9.4eY8kAcK2job.vDieEPaS50k/zAdXdXPG"
        Assertions.assertTrue(encryptionService.matchPassword(rawPassword, encryptedPassword))
    }

    @Test
    fun `should return false when checking passwords because password is incorrect`() {
        val rawPassword = "incorrectPassword"
        val encryptedPassword = "\$2a\$10\$fkgj1G42epW6RzHwdy9.4eY8kAcK2job.vDieEPaS50k/zAdXdXPG"
        Assertions.assertFalse(encryptionService.matchPassword(rawPassword, encryptedPassword))
    }

}