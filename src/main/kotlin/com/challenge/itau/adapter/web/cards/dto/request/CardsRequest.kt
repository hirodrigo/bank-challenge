package com.challenge.itau.adapter.web.cards.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import lombok.Getter

/*
 * This class represents the input data for requesting a new card.
 * It contains the customer information required to process the card request.
 *
 * @property customer The customer information associated with the card request.
*/

@Getter
data class CardsRequest(

        @field:Valid
        @field:NotNull(message = "cliente: Dados do cliente são obrigatórios")
        @JsonProperty("cliente")
        @Schema(description = "Customer information required for the card request", required = true)
        val customer: CustomerRequest?
)