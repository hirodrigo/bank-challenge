package com.challenge.itau.adapter.web.cards

import com.challenge.itau.adapter.web.cards.dto.request.CardsRequest
import com.challenge.itau.adapter.web.cards.dto.response.CardResponse
import com.challenge.itau.adapter.web.global.dto.response.error.ErrorResponse
import com.challenge.itau.core.port.`in`.CardRequestPortIn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Cards", description = "Operations related to card requests")
@RestController
class CardsController(
    private val cardRequestPortIn: CardRequestPortIn
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Operation(
        summary = "Request a card",
        description = "Evaluates customer eligibility and returns a list of eligible cards."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Eligible cards returned successfully",
                content = [io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")]
            ),
            ApiResponse(
                responseCode = "204",
                description = "No eligible cards found",
                content = []
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request data",
                content = [io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = [io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse::class)
                )]
            )
        ]
    )
    @PostMapping("/cartoes")
    fun requestCard(
        @Parameter(description = "Request payload containing customer data", required = true)
        @Valid @RequestBody cardsRequest: CardsRequest
    ): ResponseEntity<CardResponse> {

        log.info("Received request to create card: {}", cardsRequest)
        val customerData = CardsMapper.toDomain(cardsRequest)
        val cardList = cardRequestPortIn.createCardRequest(customerData)

        return CardsMapper.toAdapter(cardsRequest, cardList)

    }
}