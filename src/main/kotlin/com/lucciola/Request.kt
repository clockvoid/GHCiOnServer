package com.lucciola

import com.fasterxml.jackson.annotation.JsonProperty

enum class RequestType(val rawvalue: String) {
    STANDARD("standard")
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
    @JsonProperty("type")
    lateinit var type: RequestType
    @JsonProperty("sessionId")
    lateinit var sessionId: String
    @JsonProperty("program")
    lateinit var program: String
}

