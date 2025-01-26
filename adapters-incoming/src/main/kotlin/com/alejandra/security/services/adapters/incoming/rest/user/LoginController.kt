package com.alejandra.security.services.adapters.incoming.rest.user

import com.alejandra.security.services.adapters.incoming.model.user.LoginResponse
import com.alejandra.security.services.adapters.incoming.model.user.LoginTO
import com.alejandra.security.services.core.domain.user.port.LoginPort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//TODO: Some integration and unit tests needed for his.
@RestController
@RequestMapping("/auth")
class LoginController(private val loginPort: LoginPort) {

    //TODO: API Swagger docs needed
    @PostMapping("/login")
    fun login(@RequestBody request: LoginTO): LoginResponse {
        return LoginResponse(this.loginPort.login(request.username, request.password))
    }

}