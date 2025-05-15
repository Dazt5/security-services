package com.alejandra.security.services.adapters.incoming.rest.user

import com.alejandra.security.aop.annotation.UserIdentifier
import com.alejandra.security.services.core.domain.user.model.UserAccount
import com.alejandra.security.services.core.domain.user.port.UserDetailsPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userDetailsPort: UserDetailsPort) {

    @GetMapping
    fun getUser(@UserIdentifier username: String): UserAccount {
        return userDetailsPort.getUserDetails(username)
    }

}