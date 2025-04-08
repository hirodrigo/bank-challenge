package com.challenge.itau.adapter.web.cards.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

/*
 * This class represents the output data for a customer.
 * It contains the necessary fields to display customer information.
*/

@Schema(description = "Response payload containing customer information")
data class CustomerResponse(

        @JsonProperty("nome")
        @Schema(description = "Customer's full name", example = "Rodrigo Dev", required = true)
        val name: String,

        @JsonProperty("cpf")
        @Schema(description = "Customer's CPF (Cadastro de Pessoas Físicas)", example = "12345678900", required = true)
        val cpf: String,

        @JsonProperty("idade")
        @Schema(description = "Customer's age", example = "30", required = true)
        val age: Int,

        @JsonProperty("data_nascimento")
        @Schema(description = "Customer's birth date", example = "1993-05-15", required = true)
        val birthDate: LocalDate,

        @JsonProperty("uf")
        @Schema(description = "State where the customer resides", example = "SP", required = true)
        val state: String,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        @JsonProperty("renda_mensal")
        @Schema(description = "Customer's monthly income", example = "5000.00", required = true)
        val monthlyIncome: BigDecimal,

        @JsonProperty("email")
        @Schema(description = "Customer's email address", example = "rodrigo@email.com", required = true)
        val email: String,

        @JsonProperty("telefone_whatsapp")
        @Schema(description = "Customer's WhatsApp phone number", example = "+5511999999999", required = true)
        val whatsappPhone: String
)