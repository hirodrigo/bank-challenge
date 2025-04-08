package com.challenge.itau.adapter.web.cards

import com.challenge.itau.TestUtils
import com.challenge.itau.adapter.web.cards.dto.response.CardResponse
import com.challenge.itau.core.port.`in`.CardRequestPortIn
import com.challenge.itau.core.service.CardRequestService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.http.ResponseEntity

class CardsControllerTest {

    private val cardRequestPortIn: CardRequestPortIn = mock(CardRequestService::class.java)
    private val cardsController = CardsController(cardRequestPortIn)

    @Test
    fun `should return card response successfully`() {
        val cardsRequest = TestUtils.cardRequest
        val cardList = TestUtils.cardList
        `when`(cardRequestPortIn.createCardRequest(any())).thenReturn(cardList)

        val response: ResponseEntity<CardResponse> = cardsController.requestCard(cardsRequest)

        assertEquals(200, response.statusCode.value())
        verify(cardRequestPortIn, times(1)).createCardRequest(any())
    }

    @Test
    fun `should throw exception when card request fails`() {
        val cardsRequest = TestUtils.cardRequest
        `when`(cardRequestPortIn.createCardRequest(any())).thenThrow(RuntimeException("Service unavailable"))

        val exception = assertThrows<RuntimeException> {
            cardsController.requestCard(cardsRequest)
        }
        assertEquals("Service unavailable", exception.message)
        verify(cardRequestPortIn, times(1)).createCardRequest(any())
    }
}