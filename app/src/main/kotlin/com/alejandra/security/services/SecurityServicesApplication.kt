package com.alejandra.security.services

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["com.alejandra"])
@EnableJpaRepositories(basePackages = ["com.alejandra"])
@PropertySource("jwt.properties")
class SecurityServicesApplication

fun main(args: Array<String>) {
    runApplication<SecurityServicesApplication>(*args)
}
