package com.challenge.itau.adapter.web.cards

import com.challenge.itau.adapter.web.cards.dto.request.CardsRequest
import com.challenge.itau.adapter.web.cards.dto.response.CardResponse
import com.challenge.itau.adapter.web.cards.dto.response.CustomerResponse
import com.challenge.itau.core.domain.Card
import com.challenge.itau.core.domain.Customer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import java.util.UUID

/*
 * This class is responsible for mapping between the request and response objects
 * for card-related operations.
 *
 * It contains methods to convert a CardsRequest object to a Customer object,
 * and to convert a CardsRequest object and a list of Card objects to a CardResponse object.
 */
class CardsMapper {

    companion object {

        fun toDomain(cardsRequest: CardsRequest): Customer {
            return Customer(
                    id = null,
                    cpf = cardsRequest.customer!!.cpf!!,
                    age = cardsRequest.customer!!.age!!,
                    state = cardsRequest.customer!!.state!!,
                    monthlyIncome = cardsRequest.customer!!.monthlyIncome!!
            )
        }

        fun toAdapter(cardsRequest: CardsRequest, cartoes: List<Card>): ResponseEntity<CardResponse> {

            if (cartoes.isEmpty()) {
                return ResponseEntity(HttpStatus.NO_CONTENT)
            }

            return ResponseEntity(
                    CardResponse(
                            requestNumber = UUID.randomUUID().toString(),
                            requestDate = LocalDateTime.now(),
                            customer = toCustomerResponse(cardsRequest),
                            offeredCards = cartoes.map { card ->
                                com.challenge.itau.adapter.web.cards.dto.response.OfferedCardsResponse(
                                        cardType = card.cardType.localizedName,
                                        monthlyFee = card.monthlyFee.setScale(2),
                                        availableLimit = card.availableLimit.setScale(2),
                                        status = card.status.localizedStatus
                                )
                            }
                    ),
                    HttpStatus.OK
            )
        }

        private fun toCustomerResponse(cardsRequest: CardsRequest): CustomerResponse {
            return CustomerResponse(
                    name = cardsRequest.customer!!.name!!,
                    cpf = cardsRequest.customer!!.cpf!!,
                    age = cardsRequest.customer!!.age!!,
                    birthDate = cardsRequest.customer!!.birthDate!!,
                    state = cardsRequest.customer!!.state!!,
                    monthlyIncome = cardsRequest.customer!!.monthlyIncome!!,
                    email = cardsRequest.customer!!.email!!,
                    whatsappPhone = cardsRequest.customer!!.whatsappPhone!!
            )
        }
    }
}