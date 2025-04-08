package com.challenge.itau.core.service.decisions

import com.challenge.itau.TestUtils
import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.service.decisions.conditions.MinIncomeEvaluator
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.math.BigDecimal

class CardWithoutAnnualFeeServiceTest {

    private val minIncome = BigDecimal("3000.00")
    private val service = CardWithoutAnnualFeeService(minIncome)

    @Test
    fun `should approve card when evaluator approves`() = runBlocking {
        val customer = TestUtils.customer
        val mockMinIncomeEvaluator = mock(MinIncomeEvaluator::class.java)
        `when`(mockMinIncomeEvaluator.evaluate(customer)).thenReturn(CardStatus.APPROVED)

        val result = service.decideCard(customer)

        assertEquals(CardStatus.APPROVED, result.status)
    }

    @Test
    fun `should decline card when evaluator declines`() = runBlocking {
        val customer = TestUtils.customerLowIncome

        val mockMinIncomeEvaluator = mock(MinIncomeEvaluator::class.java)
        `when`(mockMinIncomeEvaluator.evaluate(customer)).thenReturn(CardStatus.DECLINED)

        val result = service.decideCard(customer)

        assertEquals(CardStatus.DECLINED, result.status)
    }
}