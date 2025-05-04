package com.alejandra.security.services.adapters.outgoing.db.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.ZoneId
import java.time.ZonedDateTime

@MappedSuperclass
abstract class CreationAuditableEntity(
    @Column(nullable = false, updatable = false)
    var createdOn: Long = ZonedDateTime.now(ZoneId.of("UTC")).toEpochSecond(),
    @Column(nullable = false, updatable = false)
    var createdBy: String,
)