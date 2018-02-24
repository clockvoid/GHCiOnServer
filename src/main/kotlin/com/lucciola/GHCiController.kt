package com.lucciola

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

class BadRequestException(override val message: String) : RuntimeException(message)

@RestController
class GHCiController {
    private val sessionManager = SessionManager()

    @RequestMapping(value = "/ghci", method = [RequestMethod.POST], produces = ["application/json"])
    fun ghci(@RequestBody body: Request): Result {
        return when (sessionManager.isEnabled(body.sessionId)) {
            true -> {
                val message: String = sessionManager.getGHCi(body.sessionId).submitProgram(body.program)
                StandardResult(Result.STANDARD, message, HttpStatus.OK)
            }
            else -> {
                throw BadRequestException("SeessionID " + body.sessionId + " is disabled.")
            }
        }
    }

    @RequestMapping(value = "/create_session", method = [RequestMethod.GET])
    fun createSession(): Result {
        val hash: String = this.sessionManager.makeSession()
        return FirstConnection(hash, HttpStatus.OK)
    }
}

@RestControllerAdvice
class GHCiControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException(exception: BadRequestException): Result {
        return ErrorResult(exception.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleAllError(exception: Exception): Result {
        return ErrorResult(exception.stackTrace.toString(), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}