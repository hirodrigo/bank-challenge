package com.desafio.itau.adapter.http.cards.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

/*
 * This class represents the output data for an offered card.
 * It contains the necessary fields to display card information.
 *
 * @property cardType The type of the card.
 * @property monthlyFee The monthly fee for the card.
 * @property availableLimit The available limit on the card.
 * @property status The status of the card offer.
*/

data class OfferedCardsResponse(

        @JsonProperty("tipo_cartao")
        val cardType: String,

        @JsonProperty("valor_anuidade_mensal")
        val monthlyFee: BigDecimal,

        @JsonProperty("valor_limite_disponivel")
        val availableLimit: BigDecimal,

        val status: String
)