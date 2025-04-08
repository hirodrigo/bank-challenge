package com.challenge.itau.core.port.out

fun interface GetCustomerPortOut {
    fun getCustomerId(cpf: String): String
}