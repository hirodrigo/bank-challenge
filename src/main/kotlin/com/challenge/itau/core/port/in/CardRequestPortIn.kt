package com.challenge.itau.core.port.`in`

import com.challenge.itau.core.domain.Customer

interface CardRequestPortIn {
    fun createCardRequest(customer: Customer): List<com.challenge.itau.core.domain.Card>
}