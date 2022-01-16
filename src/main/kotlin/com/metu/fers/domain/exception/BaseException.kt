package com.metu.fers.domain.exception

abstract class BaseException(override val message: String, val paramaters: Map<String, Any> = mapOf()) : RuntimeException()

