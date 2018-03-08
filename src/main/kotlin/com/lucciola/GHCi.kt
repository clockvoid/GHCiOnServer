package com.lucciola

import java.io.*

class GHCi(private val processName: String) {
    lateinit var process: Process
        private set
    lateinit var reader: BufferedReader
        private set
    lateinit var writer: BufferedWriter
        private set
    var functions: Array<String> = arrayOf()
    private val functionRegex: Regex = "^let .*".toRegex()
    private val invalidProgramRegex: Regex = "(^:(?!m(odule)?).*)|(.*readFile.*)|(^:m(odule)?\\sSystem.*)".toRegex()

    private fun makeProcess() {
        this.process = ProcessBuilder(processName).start()
    }

    private fun initWriter() {
        this.writer = BufferedWriter(OutputStreamWriter(this.process.outputStream))
    }

    private fun initReader() {
        this.reader = BufferedReader(InputStreamReader(this.process.inputStream))
    }

    fun submitProgram(program: String): String {
        when {
            this.invalidProgramRegex.matches(program) -> return "Invalid Program!"
            this.functionRegex.matches(program) -> addFunction(program)
        }
        this.makeProcess()
        this.initWriter()
        this.initReader()
        this.writer.use { out ->
            out.write(program.replace("\n$", ""))
            out.newLine()
            out.write(":q")
            out.newLine()
        }
        var str = ""
        val builder = StringBuilder()
        while (!Regex(".*[pP]relude.*").matches(str)) {
            str = reader.readLine()
            if (!Regex("^Prelude>.*").matches(str)) {
                builder.append("")
            } else {
                builder.append(str)
            }
        }
        return builder.toString()
    }

    fun getFunctions(): String {
        val result = StringBuilder()
        for (str in this.functions) {
            result.append(str)
        }
        return result.toString()
    }

    fun addFunction(arg0: String) {
        this.functions = this.functions + arrayOf(arg0)
    }
}