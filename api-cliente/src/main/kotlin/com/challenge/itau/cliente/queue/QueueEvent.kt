package com.challenge.itau.cliente.queue

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class QueueEvent(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${spring.kafka.topic.name}") private val topicName: String
) : QueueEventAdapter {

    private val objectMapper = ObjectMapper()

    override fun queueEvent(cpf: String, id: String) {
        val message = Event(cpf, id, LocalDateTime.now().toString())
        val messageJson = objectMapper.writeValueAsString(message)

        kafkaTemplate.send(topicName, messageJson)
            .toCompletableFuture()
            .thenAccept { result ->
                println("Mensagem enviada com sucesso para o tópico ${result.recordMetadata.topic()} no offset ${result.recordMetadata.offset()}")
            }
            .exceptionally { ex ->
                println("Falha ao enviar mensagem: ${ex.message}")
                null
            }
    }
}

data class Event(
    val cpf: String,
    val uuid: String,
    val date: String
)