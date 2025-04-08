package com.challenge.itau.adapter.web.cards.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

/*
 * This class represents the output data for a card request.
 * It contains the request number, request date, customer information,
 * and a list of offered cards.
 *
 * @property requestNumber The unique identifier for the card request.
 * @property requestDate The date and time when the card request was made.
 * @property customer The customer information associated with the card request.
 * @property offeredCards A list of cards offered to the customer.
 */

data class CardResponse(

        @JsonProperty("numero_solicitacao")
        val requestNumber: String,

        @JsonProperty("data_solicitacao")
        val requestDate: LocalDateTime,

        @JsonProperty("cliente")
        val customer: CustomerResponse?,

        @JsonProperty("cartoes_ofertados")
        val offeredCards: List<OfferedCardsResponse>
)



