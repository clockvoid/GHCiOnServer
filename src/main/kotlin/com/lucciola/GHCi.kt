package com.lucciola

import java.io.*

class GHCi {
    lateinit var process: Process
        private set
    lateinit var reader: BufferedReader
        private set
    lateinit var writer: BufferedWriter
        private set

    private fun makeProcess() {
        this.process = ProcessBuilder("ghci").start()
    }

    private fun initWriter() {
        this.writer = BufferedWriter(OutputStreamWriter(this.process.outputStream))
    }

    private fun initReader() {
        this.reader = BufferedReader(InputStreamReader(this.process.inputStream))
    }

    fun submitProgram(program: String): String {
        this.makeProcess()
        this.initWriter()
        this.initReader()
        this.writer.use { out -> out.write(program.replace("\n$", "")); out.newLine() }
        var str: String = ""
        val builder = StringBuilder()
        while (!Regex(".*[pP]relude.*").matches(str)) {
            str = reader.readLine()
            if (str.equals("GHCi, version 8.2.2: http://www.haskell.org/ghc/  :? for help"))str = ""
            builder.append(str)
        }
        return builder.toString()
    }
}