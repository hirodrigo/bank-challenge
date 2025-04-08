package com.challenge.itau.cliente

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class ApiClienteApplication

fun main(args: Array<String>) {
	runApplication<ApiClienteApplication>(*args)
}
