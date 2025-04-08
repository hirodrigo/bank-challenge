package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.math.BigDecimal

class CombinedEvaluatorTest {

    private val mockEvaluator1 = mock(ConditionEvaluator::class.java)
    private val mockEvaluator2 = mock(ConditionEvaluator::class.java)
    private val combinedEvaluator = CombinedEvaluator(listOf(mockEvaluator1, mockEvaluator2))

    @Test
    fun `should return DECLINED when all evaluators return DECLINED`() {
        val customer = Customer("", "", 30, "SP", BigDecimal("5000.00"))
        `when`(mockEvaluator1.evaluate(customer)).thenReturn(CardStatus.DECLINED)
        `when`(mockEvaluator2.evaluate(customer)).thenReturn(CardStatus.DECLINED)

        val result = combinedEvaluator.evaluate(customer)

        assertEquals(CardStatus.DECLINED, result)
    }

    @Test
    fun `should return APPROVED when any evaluator returns APPROVED`() {
        val customer = Customer("", "", 25, "RJ", BigDecimal("4000.00"))
        `when`(mockEvaluator1.evaluate(customer)).thenReturn(CardStatus.APPROVED)
        `when`(mockEvaluator2.evaluate(customer)).thenReturn(CardStatus.DECLINED)

        val result = combinedEvaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should return APPROVED when all evaluators return APPROVED`() {
        val customer = Customer("", "", 28, "MG", BigDecimal("6000.00"))
        `when`(mockEvaluator1.evaluate(customer)).thenReturn(CardStatus.APPROVED)
        `when`(mockEvaluator2.evaluate(customer)).thenReturn(CardStatus.APPROVED)

        val result = combinedEvaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }
}