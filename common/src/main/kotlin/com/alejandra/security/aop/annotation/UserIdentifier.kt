package com.alejandra.security.aop.annotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class UserIdentifier(val value: String = "email")
