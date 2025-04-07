package com.desafio.itau.adapter.http.cards

import com.desafio.itau.adapter.http.cards.dto.request.CardsRequest
import com.desafio.itau.adapter.http.cards.dto.response.CardResponse
import com.desafio.itau.adapter.http.cards.dto.response.OfferedCardsResponse
import com.desafio.itau.core.domain.CardType
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDateTime

@RestController
class CardsController {

    @PostMapping("/cartoes")
    fun requestCard(@Valid @RequestBody cardsRequest: CardsRequest): ResponseEntity<CardResponse> {
        val offeredCard =
                CardResponse(
                        requestNumber = "123456789",
                        requestDate = LocalDateTime.now(),
                        customer = CardsMapper.toCustomerResponse(cardsRequest),
                        offeredCards = listOf(OfferedCardsResponse(
                                cardType = CardType.CARTAO_SEM_ANUIDADE.toString(),
                                monthlyFee = BigDecimal("0.00"),
                                availableLimit = CardType.CARTAO_SEM_ANUIDADE.availableLimit,
                                status = "APPROVED"
                        ))
                )


        return ResponseEntity(offeredCard, HttpStatus.OK)
    }
}