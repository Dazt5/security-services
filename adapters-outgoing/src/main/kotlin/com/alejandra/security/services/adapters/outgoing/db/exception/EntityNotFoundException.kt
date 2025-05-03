package com.alejandra.security.services.adapters.outgoing.db.exception

class EntityNotFoundException(override val message: String) : RuntimeException(message)