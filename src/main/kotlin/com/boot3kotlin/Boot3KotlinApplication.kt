package com.boot3kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication()
@EnableJpaAuditing
class Boot3KotlinApplication

fun main(args: Array<String>)  {
	runApplication<Boot3KotlinApplication>(*args)
}
