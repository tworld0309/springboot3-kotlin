package com.boot3kotlin.controller

import com.boot3kotlin.kafka.KafkaProducer
import com.boot3kotlin.kafka.Person
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class KafkaController constructor(
        val service: KafkaProducer
){

    @GetMapping("/kafka/info", produces = ["application/json"])
    fun info(@Value("\${server.port}") _port: String) : String{
        return "[kotlin] Kafka 서비스의 기본 동작 Port: {$_port}";
    }

    @PostMapping("/kafka/producer/send/simple")
    fun send(@RequestBody param:Map<String , Any>, request: HttpServletRequest): ResponseEntity<String> =
            service.sendMessage(param["message"] as String)
                    .run {
                        ResponseEntity.ok("OK")
                    }

    @PostMapping("/kafka/producer/send/data")
    fun send(@RequestBody param: Person, request: HttpServletRequest):ResponseEntity<String> =
            service.sendMessage(param).run {
                ResponseEntity.ok("OK")
            }

}