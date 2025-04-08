package com.challenge.itau.core.service.decisions

import CardDecisionService
import com.challenge.itau.TestUtils
import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.service.decisions.conditions.ConditionEvaluator
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CardDecisionServiceTest {

    private val mockEvaluatorApproved = mock(ConditionEvaluator::class.java)
    private val mockEvaluatorDeclined = mock(ConditionEvaluator::class.java)

    private val cardType = CardType.CARD_WITH_CASHBACK

    private val service = object : CardDecisionService(cardType, listOf(mockEvaluatorApproved, mockEvaluatorDeclined)) {}

    @Test
    fun `should approve card when all evaluators approve`() = runBlocking {
        val customer = TestUtils.customer
        `when`(mockEvaluatorApproved.evaluate(customer)).thenReturn(CardStatus.APPROVED)
        `when`(mockEvaluatorDeclined.evaluate(customer)).thenReturn(CardStatus.APPROVED)

        val result = service.decideCard(customer)

        assertEquals(CardStatus.APPROVED, result.status)
    }

    @Test
    fun `should decline card when any evaluator declines`() = runBlocking {
        val customer = TestUtils.customer
        `when`(mockEvaluatorApproved.evaluate(customer)).thenReturn(CardStatus.APPROVED)
        `when`(mockEvaluatorDeclined.evaluate(customer)).thenReturn(CardStatus.DECLINED)

        val result = service.decideCard(customer)

        assertEquals(CardStatus.DECLINED, result.status)
    }
}