package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MinAgeEvaluatorTest {

    private val minAge = 18
    private val evaluator = MinAgeEvaluator(minAge)

    @Test
    fun `should approve when customer age is greater than min age`() {
        val customer = Customer("", "", 25, "", BigDecimal.ZERO)

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should approve when customer age is equal to min age`() {
        val customer = Customer("", "", 18, "", BigDecimal.ZERO)

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should decline when customer age is less than min age`() {
        val customer = Customer("", "", 16, "", BigDecimal.ZERO)

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.DECLINED, result)
    }
}