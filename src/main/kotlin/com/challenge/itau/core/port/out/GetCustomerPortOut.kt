package com.challenge.itau.core.port.out

interface GetCustomerPortOut {
    fun getCustomerId(cpf: String): String
}