package com.alejandra.security.aop.processor

import com.alejandra.security.aop.annotation.UserIdentifier
import com.alejandra.security.services.core.domain.exception.UnauthorizedException
import com.alejandra.security.jwt.JwtTokenProviderService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserIdentifierResolver(
    private val jwtTokenProviderService: JwtTokenProviderService,
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserIdentifier::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): String {
        val authHeader = webRequest.getHeader("Authorization")
            ?: throw UnauthorizedException("No Authorization header found")

        if (!authHeader.startsWith("Bearer ")) {
            throw UnauthorizedException("Invalid token type")
        }

        val token = authHeader.substring(7)

        val annotation = parameter.getParameterAnnotation(UserIdentifier::class.java)
        //TODO: refactor jwtTokenProvider to return claims and get from claims more data.
        val field = annotation?.value ?: "email"

        return jwtTokenProviderService.validateToken(token)
    }
}

