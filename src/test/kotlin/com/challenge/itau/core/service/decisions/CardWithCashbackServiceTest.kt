package com.challenge.itau.core.service.decisions

import com.challenge.itau.TestUtils
import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.service.decisions.conditions.MinAgeEvaluator
import com.challenge.itau.core.service.decisions.conditions.MinIncomeEvaluator
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.math.BigDecimal

class CardWithCashbackServiceTest {

    private val minIncome = BigDecimal("5000.00")
    private val minAge = 18

    private val mockMinIncomeEvaluator = mock(MinIncomeEvaluator::class.java)
    private val mockMinAgeEvaluator = mock(MinAgeEvaluator::class.java)

    private val service = CardWithCashbackService(minIncome, minAge)

    @Test
    fun `should approve card when all evaluators approve`() = runBlocking {
        val customer = TestUtils.customer
        `when`(mockMinIncomeEvaluator.evaluate(customer)).thenReturn(CardStatus.APPROVED)
        `when`(mockMinAgeEvaluator.evaluate(customer)).thenReturn(CardStatus.APPROVED)

        val result = service.decideCard(customer)

        assertEquals(CardStatus.APPROVED, result.status)
    }

    @Test
    fun `should decline card when any evaluator declines`() = runBlocking {
        val customer = TestUtils.customerLowIncome
        `when`(mockMinIncomeEvaluator.evaluate(customer)).thenReturn(CardStatus.DECLINED)
        `when`(mockMinAgeEvaluator.evaluate(customer)).thenReturn(CardStatus.APPROVED)

        val result = service.decideCard(customer)

        assertEquals(CardStatus.DECLINED, result.status)
    }
}