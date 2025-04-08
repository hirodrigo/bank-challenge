package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MinIncomeEvaluatorTest {

    private val minIncome = BigDecimal("3000.00")
    private val evaluator = MinIncomeEvaluator(minIncome)

    @Test
    fun `should approve when customer income is greater than min income`() {
        val customer = Customer("", "", 30, "", BigDecimal("5000.00"))

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should approve when customer income is equal to min income`() {
        val customer = Customer("", "", 30, "", BigDecimal("3000.00"))

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should decline when customer income is less than min income`() {
        val customer = Customer("", "", 30, "", BigDecimal("2000.00"))

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.DECLINED, result)
    }
}