package com.lucciola

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/ghci")
class GHCiController {
    val ghci = GHCi()

    @RequestMapping(method = [RequestMethod.POST])
    fun ghci(@RequestBody body: Request): Result {
        return StandardResult(Result.STANDARD, ghci.submitProgram(body.program), 200)
    }
}