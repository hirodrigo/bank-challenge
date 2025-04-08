package com.challenge.itau.adapter.web.cards.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import lombok.ToString
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import java.time.LocalDate


/*
 * This class represents the input data for a customer.
 * It contains the necessary fields to create or update a customer in the system.
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

data class CustomerRequest(

        @field:NotBlank(message = "nome: Nome é obrigatório")
        @JsonProperty("nome")
        val name: String?,

        @field:NotBlank(message = "cpf: CPF é obrigatório")
        @field:CPF(message = "cpf: CPF deve ser válido")
        @ToString.Exclude
        @JsonProperty("cpf")
        val cpf: String?,

        @field:NotNull(message = "idade: Idade é obrigatória")
        @field:Min(value = 18, message = "Idade deve ser maior ou igual a 18")
        @JsonProperty("idade")
        val age: Int?,

        @field:NotNull(message = "data_nascimento: Data de nascimento é obrigatória")
        @JsonProperty("data_nascimento")
        val birthDate: LocalDate?,

        @field:NotBlank(message = "uf: Estado é obrigatório")
        @JsonProperty("uf")
        val state: String?,

        @field:NotNull(message = "renda_mensal: Renda mensal é obrigatória")
        @field:DecimalMin(value = "0.0", inclusive = false, message = "Renda mensal deve ser maior que 0")
        @JsonProperty("renda_mensal")
        val monthlyIncome: BigDecimal?,

        @field:NotBlank(message = "email: Email é obrigatório")
        @field:Email(message = "Email deve ser válido")
        @ToString.Exclude
        @JsonProperty("email")
        val email: String?,

        @field:NotBlank(message = "telefone_whatsapp: Telefone WhatsApp é obrigatório")
        @ToString.Exclude
        @JsonProperty("telefone_whatsapp")
        val whatsappPhone: String?
)