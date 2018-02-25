package com.lucciola

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod

@RestController
class GHCiController {
    @Autowired
    private lateinit var sessionManager: SessionManager

    @RequestMapping(value = "/ghci", method = [RequestMethod.POST], produces = ["application/json"])
    fun ghci(@RequestBody body: Request): Result {
        return StandardResult(Result.STANDARD, this.sessionManager.postProgram(body.type, body.sessionId, body.program), HttpStatus.OK.value())
    }

    @RequestMapping(value = "/create_session", method = [RequestMethod.GET])
    fun createSession(): Result {
        val hash: String = this.sessionManager.makeSession()
        return FirstConnection(hash, HttpStatus.OK.value())
    }
}

