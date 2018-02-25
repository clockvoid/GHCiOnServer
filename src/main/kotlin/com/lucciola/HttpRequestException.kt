package com.lucciola

abstract class HttpRequestException(override val message: String) : RuntimeException(message)

class BadRequestException(override val message: String) : HttpRequestException(message)
