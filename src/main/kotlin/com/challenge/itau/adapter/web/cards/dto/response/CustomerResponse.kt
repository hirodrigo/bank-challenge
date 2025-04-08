package com.challenge.itau.adapter.web.cards.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate

/*
 * This class represents the output data for a customer.
 * It contains the necessary fields to display customer information.
 *
 * @property name The name of the customer.
 * @property cpf The CPF (Cadastro de Pessoas Físicas) of the customer.
 * @property age The age of the customer.
 * @property birthDate The birthdate of the customer.
 * @property state The state where the customer resides.
 * @property monthlyIncome The monthly income of the customer.
 * @property email The email address of the customer.
 * @property whatsappPhone The WhatsApp phone number of the customer.
*/

data class CustomerResponse(

        @JsonProperty("nome")
        val name: String,

        @JsonProperty("cpf")
        val cpf: String,

        @JsonProperty("idade")
        val age: Int,

        @JsonProperty("data_nascimento")
        val birthDate: LocalDate,

        @JsonProperty("estado")
        val state: String,

        @JsonProperty("renda_mensal")
        val monthlyIncome: BigDecimal,

        @JsonProperty("email")
        val email: String,

        @JsonProperty("telefone_whatsapp")
        val whatsappPhone: String
)