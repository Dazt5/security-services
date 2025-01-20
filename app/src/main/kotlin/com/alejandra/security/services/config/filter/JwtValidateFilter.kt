package com.alejandra.security.services.config.filter

import com.alejandra.security.jwt.JwtValidatorService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtValidateFilter(private val jwtValidatorService: JwtValidatorService) : OncePerRequestFilter() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(JwtValidateFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        LOGGER.info("Validation authentication")
        val authHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?: request.getHeader(HttpHeaders.AUTHORIZATION.lowercase())
            ?: request.getHeader(HttpHeaders.AUTHORIZATION.uppercase())
        authHeader?.let { authorizationHeader ->
            val token: String = authorizationHeader.replace("Bearer ", "")
            val username: String = jwtValidatorService.validateToken(token)
            //TODO: find this username in the database to check if it already exists.
        } ?: run {
            LOGGER.info("No authorization header found in request, sending exception")
            //TODO: Change exception class for a custom one
            throw IllegalArgumentException("No authentication provided.")
        }
        filterChain.doFilter(request, response)
    }
}