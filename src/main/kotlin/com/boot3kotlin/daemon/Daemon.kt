package com.boot3kotlin.daemon

import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.concurrent.thread

// fun Main(args: Array<String>){
fun daemonMain(args: Array<String>){
    //    thread(start = true, isDaemon = false) {
    println("### args : $args");
    thread(start = true, isDaemon = false) { // isDaemon이 false 이면 종료, true 이면 지속
        while (true) {
            Thread.sleep(1000L)
            println(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
        }
    }
    println("main finished.")
}