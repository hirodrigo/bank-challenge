package com.challenge.itau.cliente.queue

interface QueueEventAdapter {
    fun queueEvent(cpf: String, id: String)
}