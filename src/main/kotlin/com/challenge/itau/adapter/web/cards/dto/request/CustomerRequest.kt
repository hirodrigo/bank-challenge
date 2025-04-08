package com.challenge.itau.adapter.web.cards.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import java.time.LocalDate


/*
 * This class represents the input data for a customer.
 * It contains the necessary fields to create or update a customer in the system.
 */

@Schema(description = "Request payload containing customer information")
data class CustomerRequest(

        @field:NotBlank(message = "nome: Nome é obrigatório")
        @JsonProperty("nome")
        @Schema(description = "Customer's full name", example = "Rodrigo Dev", required = true)
        val name: String?,

        @field:NotBlank(message = "cpf: CPF é obrigatório")
        @field:CPF(message = "cpf: CPF deve ser válido")
        @JsonProperty("cpf")
        @Schema(description = "Customer's CPF (Cadastro de Pessoas Físicas)", example = "12345678900", required = true)
        val cpf: String?,

        @field:NotNull(message = "idade: Idade é obrigatória")
        @field:Min(value = 18, message = "Idade deve ser maior ou igual a 18")
        @JsonProperty("idade")
        @Schema(description = "Customer's age", example = "30", required = true)
        val age: Int?,

        @field:NotNull(message = "data_nascimento: Data de nascimento é obrigatória")
        @JsonProperty("data_nascimento")
        @Schema(description = "Customer's birth date", example = "2002-05-15", required = true)
        val birthDate: LocalDate?,

        @field:NotBlank(message = "uf: Estado é obrigatório")
        @JsonProperty("uf")
        @Schema(description = "State where the customer resides", example = "SP", required = true)
        val state: String?,

        @field:NotNull(message = "renda_mensal: Renda mensal é obrigatória")
        @field:DecimalMin(value = "0.0", inclusive = false, message = "Renda mensal deve ser maior que 0")
        @JsonProperty("renda_mensal")
        @Schema(description = "Customer's monthly income", example = "5000.00", required = true)
        val monthlyIncome: BigDecimal?,

        @field:NotBlank(message = "email: Email é obrigatório")
        @field:Email(message = "Email deve ser válido")
        @JsonProperty("email")
        @Schema(description = "Customer's email address", example = "rodrigo@email.com", required = true)
        val email: String?,

        @field:NotBlank(message = "telefone_whatsapp: Telefone WhatsApp é obrigatório")
        @JsonProperty("telefone_whatsapp")
        @Schema(description = "Customer's WhatsApp phone number", example = "+5511999999999", required = true)
        val whatsappPhone: String?
)