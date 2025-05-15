package com.alejandra.security.services.core.domain.user.port

import com.alejandra.security.services.core.domain.user.model.UserAccount

interface UserDetailsPort {

    fun getUserDetails(username: String): UserAccount

}