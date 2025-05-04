package com.alejandra.security.services.adapters.outgoing.db.user.repository

import com.alejandra.security.services.adapters.outgoing.db.exception.EntityNotFoundException
import com.alejandra.security.services.adapters.outgoing.db.user.entity.UserAccountEntity
import com.alejandra.security.services.adapters.outgoing.db.user.mapper.UserAccountMapper
import com.alejandra.security.services.core.domain.user.model.UserAccount
import com.alejandra.security.services.core.domain.user.port.UserAccountRepositoryPort
import org.springframework.stereotype.Repository
import java.util.Objects

//TODO: some unit tests for this
@Repository
class UserAccountRepository(
    val jpaUserAccountRepository: JpaUserAccountRepository,
) : UserAccountRepositoryPort {

    override fun findByUsername(username: String): UserAccount {
        val userAccountEntity =
            jpaUserAccountRepository.findByEmail(username) ?: throw EntityNotFoundException("User not found")
        return UserAccountMapper.toDomain(userAccountEntity)
    }

}