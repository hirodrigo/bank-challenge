package com.challenge.itau.core.domain

import java.math.BigDecimal

/*
    * This file contains the definition of the Customer data class, which represents a customer and their information
*/

data class Customer(
    var id : String?,
    val cpf: String,
    val age: Int,
    val state: String,
    val monthlyIncome: BigDecimal,
)