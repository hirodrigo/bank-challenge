package com.challenge.itau.adapter.web.cards.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/*
 * This class represents the output data for a card request.
 * It contains the request number, request date, customer information,
 * and a list of offered cards.
*/

@Schema(description = "Response payload for a card request")
data class CardResponse(

        @JsonProperty("numero_solicitacao")
        @Schema(description = "Unique identifier for the card request", example = "9d28ac20-ccc4-48b5-a114-adef3e043005", required = true)
        val requestNumber: String,

        @JsonProperty("data_solicitacao")
        @Schema(description = "Date and time when the card request was made", example = "2024-10-01T12:00:00", required = true)
        val requestDate: LocalDateTime,

        @JsonProperty("cliente")
        @Schema(description = "Customer information associated with the card request", required = true)
        val customer: CustomerResponse?,

        @JsonProperty("cartoes_ofertados")
        @Schema(description = "List of cards offered to the customer", required = true)
        val offeredCards: List<OfferedCardsResponse>
)



