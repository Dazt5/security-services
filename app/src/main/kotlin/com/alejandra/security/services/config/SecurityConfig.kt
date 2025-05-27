package com.alejandra.security.services.config

import com.alejandra.security.services.config.authManager.AdminAuthenticationManager
import com.alejandra.security.services.config.filter.JwtValidateFilter
import com.alejandra.security.services.model.ResponseError
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//TODO: Check code coverage and code smells using Sonar.
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtValidateFilter: JwtValidateFilter,
    private val objectMapper: ObjectMapper,
    @Value("\${debug.isStackTraceEnabled: false}") private val isStackTraceEnabled: Boolean
) {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf -> csrf.disable() }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(jwtValidateFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/company/**").access(AdminAuthenticationManager())
                    .anyRequest().authenticated()
            }
            .exceptionHandling { it -> it.accessDeniedHandler(customAccessDeniedHandler(objectMapper)) }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
        return http.build()
    }

    @Bean
    fun customAccessDeniedHandler(objectMapper: ObjectMapper): AccessDeniedHandler {
        return AccessDeniedHandler { request, response, ex ->
            val error = ResponseError(
                message = "Access Denied",
                httpStatus = HttpStatus.FORBIDDEN.value(),
                stackTrace = if (isStackTraceEnabled) ExceptionUtils.getStackTrace(ex) else null,
                detailedMessage = if (isStackTraceEnabled) ex.message else null
            )
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(response.outputStream, error)
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}