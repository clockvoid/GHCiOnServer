package com.lucciola

import java.io.*

class GHCi(private val processName: String) {
    lateinit var process: Process
        private set
    lateinit var reader: BufferedReader
        private set
    lateinit var writer: BufferedWriter
        private set

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
        if (Regex(".*writeFile.*|:i(mport)?\\sSystem.*").matches(program)) {
            return "Invalid program!"
        }
        this.makeProcess()
        this.initWriter()
        this.initReader()
        this.writer.use { out -> out.write(program.replace("\n$", "")); out.newLine() }
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
}