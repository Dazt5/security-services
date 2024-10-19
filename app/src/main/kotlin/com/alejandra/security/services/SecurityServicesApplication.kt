package com.alejandra.security.services

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.alejandra"])
class SecurityServicesApplication

fun main(args: Array<String>) {
    runApplication<SecurityServicesApplication>(*args)
}
