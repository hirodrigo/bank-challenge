package com.challenge.itau.cliente.controller

import com.challenge.itau.cliente.queue.QueueEventAdapter
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping
class CustomerController(private val queueAdapter: QueueEventAdapter) {

    @GetMapping("/clientes")
    fun generateUUID(@RequestParam("cpf") cpfRequest: String): UUIDResponse {
        val id = UUID.randomUUID().toString()
        queueAdapter.queueEvent(cpfRequest, id)
        return UUIDResponse(id)
    }

    data class UUIDResponse(@JsonProperty("id_cliente") val idCliente: String)
}