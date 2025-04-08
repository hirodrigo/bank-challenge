package com.challenge.itau

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ItauChallengeApplication

fun main(args: Array<String>) {
	runApplication<ItauChallengeApplication>(*args)
}