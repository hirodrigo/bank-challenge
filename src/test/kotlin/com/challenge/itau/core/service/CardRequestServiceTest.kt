package com.challenge.itau.core.service

import CardDecisionService
import com.challenge.itau.TestUtils
import com.challenge.itau.core.domain.Card
import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.port.out.GetCustomerPortOut
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.math.BigDecimal

class CardRequestServiceTest {

    private val mockCardDecisionService1 = mock(CardDecisionService::class.java)
    private val mockCardDecisionService2 = mock(CardDecisionService::class.java)
    private val mockGetCustomerPortOut = mock(GetCustomerPortOut::class.java)

    private val service = CardRequestService(
        cardDecisions = listOf(mockCardDecisionService1, mockCardDecisionService2),
        getCustomerPortOut = mockGetCustomerPortOut
    )

    @Test
    fun `should return approved cards`() = runBlocking {
        val customer = TestUtils.customer

        val approvedCard = Card(CardType.CARD_WITHOUT_ANNUAL_FEE, BigDecimal("0.00"), BigDecimal("5000.00"), CardStatus.APPROVED)
        val declinedCard = Card(CardType.PARTNER_CARD, BigDecimal("10.00"), BigDecimal("3000.00"), CardStatus.DECLINED)

        `when`(mockGetCustomerPortOut.getCustomerId(customer.cpf)).thenReturn("123")
        `when`(mockCardDecisionService1.decideCard(customer)).thenReturn(approvedCard)
        `when`(mockCardDecisionService2.decideCard(customer)).thenReturn(declinedCard)

        val result = service.createCardRequest(customer)

        assertEquals(1, result.size)
        assertEquals(CardStatus.APPROVED, result[0].status)
    }

    @Test
    fun `should return empty list when no cards are approved`() = runBlocking {
        val customer = TestUtils.customerLowIncome

        val declinedCard1 = Card(CardType.CARD_WITHOUT_ANNUAL_FEE, BigDecimal("0.00"), BigDecimal("5000.00"), CardStatus.DECLINED)
        val declinedCard2 = Card(CardType.PARTNER_CARD, BigDecimal("10.00"), BigDecimal("3000.00"), CardStatus.DECLINED)

        `when`(mockGetCustomerPortOut.getCustomerId(customer.cpf)).thenReturn("123")
        `when`(mockCardDecisionService1.decideCard(customer)).thenReturn(declinedCard1)
        `when`(mockCardDecisionService2.decideCard(customer)).thenReturn(declinedCard2)

        val result = service.createCardRequest(customer)

        assertEquals(0, result.size)
    }
}