package com.alejandra.security.services.adapters.outgoing.db.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

@MappedSuperclass
abstract class BaseEntity(
    @Column(nullable = true, updatable = true)
    var updatedOn: Long? = null,
    @Column(nullable = true, updatable = true)
    var updatedBy: String? = null,
    @Column(nullable = false, updatable = false)
    var createdOn: Long = LocalDateTime.now(ZoneId.of("UTC")).toEpochSecond(ZoneOffset.UTC),
    @Column(nullable = false, updatable = false)
    var createdBy: String
)