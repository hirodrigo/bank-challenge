package com.challenge.itau.adapter.web.cards.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/*
 * This class represents the output data for an offered card.
 * It contains the necessary fields to display card information.
*/

@Schema(description = "Response payload containing information about an offered card")
data class OfferedCardsResponse(

        @JsonProperty("tipo_cartao")
        @Schema(description = "Type of the card offered", example = "CARTAO_SEM_ANUIDADE", required = true)
        val cardType: String,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        @JsonProperty("valor_anuidade_mensal")
        @Schema(description = "Monthly fee for the card", example = "50.00", required = true)
        val monthlyFee: BigDecimal,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        @JsonProperty("valor_limite_disponivel")
        @Schema(description = "Available limit on the card", example = "10000.00", required = true)
        val availableLimit: BigDecimal,

        @JsonProperty("status")
        @Schema(description = "Status of the card offer", example = "APPROVED", required = true)
        val status: String
)