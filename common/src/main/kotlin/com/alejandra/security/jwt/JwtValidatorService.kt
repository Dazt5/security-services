package com.alejandra.security.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtValidatorService(@Value("\${jwt.secret}") private val jwtSecret: String) {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(JwtValidatorService::class.java)
    }

    fun validateToken(token: String): String {
        val secretKey = Keys.hmacShaKeyFor(jwtSecret.encodeToByteArray())
        try {
            val claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            return requireNotNull(claims.payload.subject) {
                LOGGER.error("JWT malformed, not username available in claims token: {}", token)
                //TODO: Change exception class for a custom one.
                throw IllegalArgumentException("Not a valid request")
            }
        } catch (e: JwtException) {
            //TODO: Change exception class for a custom one.
            LOGGER.error("JWT malformed, message: {}", e.message, e)
            throw IllegalArgumentException("Invalid authorization", e)
        }
    }

}
