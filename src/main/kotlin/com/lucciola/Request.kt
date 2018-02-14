package com.lucciola

enum class RequestType(val rawvalue: String) {
    STANDARD("standard"),
    FIRSTCONNECTION("first_connection")
}

/**
 * Interface Request describes any requests from client.
 * The instance of concrete of this interface is created by Spring Boot from JSON.
 *
 * @property type describes request type
 * @property sessionId describes session id
 * @property program describes programs
 */
class Request {
    lateinit var type: RequestType
    lateinit var sessionId: String
    lateinit var program: String
}

