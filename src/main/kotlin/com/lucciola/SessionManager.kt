package com.lucciola

import java.security.MessageDigest
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import org.apache.commons.lang3.SystemUtils
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServlet

@Service
class SessionManager : HttpServlet() {
    private var sessions: Map<String, GHCi> = HashMap()

    fun postProgram(type: RequestType, sessionId: String, program: String): String {
        return when (this.isEnabled(sessionId)) {
            true -> {
                this.getGHCi(sessionId).submitProgram(program)
            }
            else -> {
                throw BadRequestException("SeessionID $sessionId is disabled.")
            }
        }
    }

    fun getGHCi(sessionId: String): GHCi {
        return sessions[sessionId]!!
    }

    private fun isEnabled(sessionId: String): Boolean {
        return when (sessions[sessionId]) {
            null -> false
            else -> true
        }
    }

    fun makeHash(): String {
        val timestampFormat1: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS")
        val rand1: Int = Random().nextInt()
        val source: String = timestampFormat1.format(Date()).replace(" ", "") + rand1.toString()
        return HashUtils.sha256(source)
    }

    fun makeSession(): String {
        val command: String = when {
            SystemUtils.IS_OS_LINUX -> "/usr/bin/ghci"
            else -> "ghci"
        }
        val ghci = GHCi(command)
        val hash: String = makeHash()
        this.sessions += hashMapOf(hash to ghci)
        return hash
    }
}

// this function made with https://www.samclarke.com/kotlin-hash-strings/
object HashUtils {
    fun sha512(input: String): String = hashString("SHA-512", input)
    fun sha256(input: String): String = hashString("SHA-256", input)
    fun sha1(input: String): String = hashString("SHA-1", input)

    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        // making message digest object, which offers an algorithm.
        val bytes = MessageDigest.getInstance(type).digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0F])
            result.append(HEX_CHARS[i and 0x0F])
        }

        return result.toString()
    }
}
