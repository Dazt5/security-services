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
        val jwtService = JwtTokenProviderService(secret, Duration.ofSeconds(3600))
        val jwtToken: String = jwtService.generateToken(username)
        //assert
        assertDoesNotThrow {
            Assertions.assertEquals(username, jwtService.validateToken(jwtToken))
        }
    }

    @Test
    fun givenACorrectTokenButNotUsernamePresent_whenValidatingJwt_ThenRaiseAnError() {
        //arrange
        val secret = "IDs6OL0iHmY8eyUTtFjk2frVX0R3KwJZ0hIJGhYoam0="
        //act
        val jwtTokenProviderService = JwtTokenProviderService(secret, Duration.ofSeconds(3600))
        val jwtToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzczMzY1MDAsImV4cCI6ODY0MDE3MzcyNTAxMDB9.xWGF9VqkptlyWdM_yHghAtM4a_deZz8eOgBZznFTeE4"
        //assert
        assertThrows<IllegalArgumentException> {
            jwtTokenProviderService.validateToken(jwtToken)
        }
    }

    @Test
    fun givenAnExpiredToken_whenValidatingJwt_ThenRaiseAnError() {
        //arrange
        val secret = secretGenerator()
        val username = "test"
        //act
        val jwtTokenProviderService = JwtTokenProviderService(secret, Duration.ofSeconds(-3600))
        val jwtToken: String = jwtTokenProviderService.generateToken(username)
        //assert
        assertThrows<IllegalArgumentException> {
            jwtTokenProviderService.validateToken(jwtToken)
        }
    }

    @Test
    fun givenATamperedToken_whenValidatingJwt_ThenRaiseAnError() {
        //arrange
        val secret = secretGenerator()
        val tamperedToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzM3MzE3OTc0LCJleHAiOjE3MzczMjE1NzR9.Vr_lh1vFdvXyJmVf-_cQd6f33wkJu6JmQIstHHunZ2M"
        //act
        val jwtTokenProviderService = JwtTokenProviderService(secret, Duration.ofSeconds(-3600))
        //assert
        assertThrows<IllegalArgumentException> { jwtTokenProviderService.validateToken(tamperedToken) }
    }

    @Test
    fun givenATokenWithDifferentSecret_whenValidatingJwt_ThenRaiseAnError() {
        //act
        val jwtTokenProviderService = JwtTokenProviderService(secretGenerator(), Duration.ofSeconds(-3600))
        val token = jwtTokenProviderService.generateToken("test")
        //assert
        assertThrows<IllegalArgumentException> { jwtTokenProviderService.validateToken(token) }
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