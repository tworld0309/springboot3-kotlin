package com.boot3kotlin.daemon

import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.concurrent.thread

fun main(args: Array<String>){
    //    thread(start = true, isDaemon = false) {
    thread(start = true, isDaemon = false) {
        while (true) {
            Thread.sleep(1000L)
            println(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
        }
    }
    println("main finished.")
}