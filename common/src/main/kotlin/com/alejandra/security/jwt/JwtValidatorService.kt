package com.alejandra.security.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtValidatorService(@Value("\${jwt.secret}") private val jwtSecret: String) {

    fun validateToken(token: String): String {
        val secretKey = Keys.hmacShaKeyFor(jwtSecret.encodeToByteArray())
        try {
            val claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            return claims.payload.subject
        } catch (e: JwtException) {
            throw IllegalArgumentException("Unauthorized Access")
        }
    }

}
