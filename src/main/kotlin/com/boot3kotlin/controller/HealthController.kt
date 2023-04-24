package com.boot3kotlin.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/info", produces = ["application/json"])
    fun info(@Value("\${server.port}") _port: String) : String{
        return "[kotlin] 서비스의 기본 동작 Port: {" + _port + "}";
    }
}