package com.challenge.itau.adapter.web.cards

import com.challenge.itau.TestUtils
import com.challenge.itau.core.domain.Card
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.domain.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.math.BigDecimal

class CardsMapperTest {

    @Test
    fun `should map CardsRequest to Customer`() {
        val cardsRequest = TestUtils.cardRequest

        val customer: Customer = CardsMapper.toDomain(cardsRequest)

        assertEquals(cardsRequest.customer!!.cpf, customer.cpf)
        assertEquals(cardsRequest.customer!!.age, customer.age)
        assertEquals(cardsRequest.customer!!.state, customer.state)
        assertEquals(cardsRequest.customer!!.monthlyIncome, customer.monthlyIncome)
    }

    @Test
    fun `should map CardsRequest and Card list to ResponseEntity`() {
        val cardsRequest = TestUtils.cardRequest
        val cardList = TestUtils.cardList

        val response = CardsMapper.toAdapter(cardsRequest, cardList)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(1, response.body!!.offeredCards.size)
        assertEquals(CardType.CARD_WITHOUT_ANNUAL_FEE.localizedName, response.body!!.offeredCards[0].cardType)
        assertEquals(BigDecimal.ZERO.setScale(2), response.body!!.offeredCards[0].monthlyFee)
        assertEquals(cardList.first().availableLimit, response.body!!.offeredCards[0].availableLimit)
    }

    @Test
    fun `should return NO_CONTENT when card list is empty`() {
        val cardsRequest = TestUtils.cardRequest
        val emptyCardList = emptyList<Card>()

        val response = CardsMapper.toAdapter(cardsRequest, emptyCardList)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }
}