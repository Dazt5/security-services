package com.alejandra.security.jwt

import com.alejandra.security.services.core.domain.exception.UnauthorizedException
import com.alejandra.security.services.core.domain.user.port.TokenProviderPort
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.Date

@Component
class JwtTokenProviderService(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expiration-time}") private val jwtExpirationTime: Duration
) : TokenProviderPort {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(JwtTokenProviderService::class.java)
    }

    override fun generateToken(username: String): String {
        val secretKey = Keys.hmacShaKeyFor(jwtSecret.encodeToByteArray())
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + jwtExpirationTime.toMillis()))
            .signWith(secretKey)
            .compact()
    }

    override fun validateToken(token: String): String {
        val secretKey = Keys.hmacShaKeyFor(jwtSecret.encodeToByteArray())
        try {
            val claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            return requireNotNull(claims.payload.subject) {
                LOGGER.error("JWT malformed, not username available in claims token: {}", token)
                throw UnauthorizedException("Not a valid request")
            }
        } catch (e: JwtException) {
            LOGGER.error("JWT malformed, message: {}", e.message, e)
            throw UnauthorizedException("Not a valid request", e)
        }
    }

}