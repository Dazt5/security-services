package com.alejandra.security.jwt

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.security.SecureRandom
import java.time.Duration
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.test.assertNotNull

class JwtServicesTest {

    @Test
    fun givenACorrectToken_whenValidatingJwt_ThenValidationEndsSuccessfully() {
        //arrange
        val secret = secretGenerator()
        val username = "test"
        //act
        val jwtGeneratorService = JwtGeneratorService(secret, Duration.ofSeconds(3600))
        val jwtValidatorService = JwtValidatorService(secret)
        val jwtToken: String = jwtGeneratorService.generateToken(username, emptyMap())
        //assert
        assertDoesNotThrow {
            Assertions.assertEquals(username, jwtValidatorService.validateToken(jwtToken))
        }
    }

    @Test
    fun givenATamperedToken_whenValidatingJwt_ThenValidation() {
        //arrange
        val secret = secretGenerator()
        val tamperedToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzM3MzE3OTc0LCJleHAiOjE3MzczMjE1NzR9.Vr_lh1vFdvXyJmVf-_cQd6f33wkJu6JmQIstHHunZ2M"
        //act
        val jwtValidatorService = JwtValidatorService(secret)
        //assert
        assertThrows<IllegalArgumentException> { jwtValidatorService.validateToken(tamperedToken) }
    }

    @Test
    fun givenATokenWithDifferentSecret_whenValidatingJwt_ThenValidation() {
        //act
        val jwtGeneratorService = JwtGeneratorService(secretGenerator(), Duration.ofSeconds(3600))
        val token = jwtGeneratorService.generateToken("test", emptyMap())
        val jwtValidatorService = JwtValidatorService(secretGenerator())
        //assert
        assertThrows<IllegalArgumentException> { jwtValidatorService.validateToken(token) }
    }

    @Test
    fun testGenerateSecret() {
        assertDoesNotThrow {
            val secret = secretGenerator()
            assertNotNull(secret)
            println(secret)
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun secretGenerator(): String {
        val secureRandom = SecureRandom()
        val secretBytes = ByteArray(32)
        secureRandom.nextBytes(secretBytes)
        return Base64.encode(secretBytes)
    }

}