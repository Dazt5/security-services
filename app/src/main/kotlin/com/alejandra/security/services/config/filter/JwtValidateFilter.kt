package com.alejandra.security.services.config.filter

import com.alejandra.security.auth.CustomUserDetailsService
import com.alejandra.security.jwt.JwtTokenProviderService
import com.alejandra.security.services.core.domain.exception.UnauthorizedException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


//TODO: some unit tests for this.
@Component
class JwtValidateFilter(
    private val jwtTokenProviderService: JwtTokenProviderService,
    private val customUserDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(JwtValidateFilter::class.java)
    }

    /**
     * Time ago I was having issues with this filter bc was filtering some request that shouldn't, using this we got that.
     * */
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        //TODO: create a way to handle all no-secured endpoints
        return request.servletPath.startsWith("/auth/")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        LOGGER.debug("Validation authentication")
        val authHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?: request.getHeader(HttpHeaders.AUTHORIZATION.lowercase())
            ?: request.getHeader(HttpHeaders.AUTHORIZATION.uppercase())
        authHeader?.let { authorizationHeader ->
            val token: String = authorizationHeader.replace("Bearer ", "")
            val username: String = jwtTokenProviderService.validateToken(token)
            if (username.isBlank()) {
                LOGGER.error("username in token not found, throwing exception")
                throw UnauthorizedException("Bad authentication")
            }
            LOGGER.debug("building custom user details for username: {}", username)
            val userDetails = customUserDetailsService.loadUserByUsername(username)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            LOGGER.info("Setting authentication in context holder for username: {}", username)
            SecurityContextHolder.getContext().authentication = authentication
        } ?: run {
            LOGGER.error("No authorization header found in request, sending exception")
            throw UnauthorizedException("Bad authentication.")
        }
        filterChain.doFilter(request, response)
    }

}