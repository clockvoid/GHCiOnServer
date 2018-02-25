package com.lucciola

import org.springframework.http.HttpStatus

/**
 * Result interface describes some kind of response of the server.
 * The interface must have some properties as follows.
 *
 * @property type type of result
 *  0 describes Standard Result returned by GHCi
 *  1 describes First Connection Result
 *  2 describes Error Result
 * @property body message body
 * @property status HTTP Status Code
 */
interface Result {
    companion object {
        const val STANDARD = 0
        const val FIRSTCONNECTION = 1
        const val ERROR = 2
    }
    val status: Int
    val type: Int
    val body: String
}

data class StandardResult(override val type: Int = Result.STANDARD, override val body: String, override val status: Int) : Result

data class FirstConnection(override val body: String, override val status: Int) : Result {
    override val type: Int = Result.FIRSTCONNECTION
}

data class ErrorResult(override val body: String, val exception: String, override val status: Int) : Result {
    override val type: Int = Result.ERROR
}
