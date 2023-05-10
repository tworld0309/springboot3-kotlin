package com.boot3kotlin.socket

import com.boot3kotlin.logger
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread


fun main(args: Array<String>) {
    logger.debug("Client 접속")
    val IP_ADDRESS = "localhost"
    val PORT_NUMBER = 9999

    val client = Client(IP_ADDRESS, PORT_NUMBER)
    client.run()
}

class Client(address: String, port: Int) {
    private val connection: Socket = Socket(address, port)
    private var connected: Boolean = true

    init {
        println("Connected to server at $address on port $port")
    }

    private val reader: Scanner = Scanner(connection.getInputStream())
    private val writer: OutputStream = connection.getOutputStream()

    fun run() {
        thread { read() }
        while (connected) {
            val input = readLine() ?: ""
            if ("exit" in input) {
                connected = false
                reader.close()
                connection.close()
            } else {
                write(input)
            }
        }

    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun read() {
        while (connected)
            println(reader.nextLine())
    }
}