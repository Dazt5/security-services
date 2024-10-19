package com.alejandra.security.services.user

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(): String {
        return "should return a user"
    }

}