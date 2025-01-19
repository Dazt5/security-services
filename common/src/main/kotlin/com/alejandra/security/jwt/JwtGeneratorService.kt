package com.alejandra.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.Date

@Component
class JwtGeneratorService(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expiration-time}") private val jwtExpirationTime: Duration
) {

    fun generateToken(username: String, claims: Map<String, Any>): String {
        val secretKey = Keys.hmacShaKeyFor(jwtSecret.encodeToByteArray())
        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + jwtExpirationTime.toMillis()))
            .signWith(secretKey)
            .compact()
    }

}