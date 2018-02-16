package com.lucciola

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class GHCiController {
    private val sessionManager = SessionManager()

    @RequestMapping(value = "/ghci", method = [RequestMethod.POST])
    fun ghci(@RequestBody body: Request): Result {
        return StandardResult(Result.STANDARD, sessionManager.getGHCi(body.sessionId).submitProgram(body.program), 200)
    }

    @RequestMapping(value = "/crateSession", method = [RequestMethod.POST])
    fun createSessoin(@RequestBody body: Request): Result {
        val hash: String = this.sessionManager.makeSession()
        return StandardResult(Result.FIRSTCONNECTION, hash, 200)
    }
}