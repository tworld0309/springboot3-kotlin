package com.boot3kotlin

import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.io.IOException
import java.net.ServerSocket

@SpringBootApplication
@EnableJpaAuditing
class Boot3KotlinApplication
var logger = KotlinLogging.logger {}

fun main(args: Array<String>)  {
	runApplication<Boot3KotlinApplication>(*args)
}
