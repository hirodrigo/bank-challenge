package com.challenge.itau.adapter.web.cards

import com.challenge.itau.adapter.web.cards.dto.request.CardsRequest
import com.challenge.itau.adapter.web.cards.dto.response.CardResponse
import com.challenge.itau.core.port.`in`.CardRequestPortIn
import jakarta.validation.Valid
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/*
 * This class handles HTTP requests related to card operations.
 * It processes card requests and returns the appropriate responses.
 *
 * @property cardRequestPortIn The port for handling card requests.
*/

@Slf4j
@RestController
class CardsController(
        private val cardRequestPortIn: CardRequestPortIn,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/cartoes")
    fun requestCard(@Valid @RequestBody cardsRequest: CardsRequest): ResponseEntity<CardResponse> {
        val correlationId = UUID.randomUUID().toString()

        log.info("Received request to create card: {}", cardsRequest)
        val customerData = CardsMapper.toDomain(cardsRequest)
        val cardList = cardRequestPortIn.createCardRequest(customerData)

        return CardsMapper.toAdapter(cardsRequest, cardList)
    }
}