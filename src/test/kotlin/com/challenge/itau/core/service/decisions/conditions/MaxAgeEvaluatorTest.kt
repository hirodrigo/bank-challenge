package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MaxAgeEvaluatorTest {

    private val maxAge = 30
    private val evaluator = MaxAgeEvaluator(maxAge)

    @Test
    fun `should approve when customer age is less than max age`() {
        val customer = Customer("", "", 25, "", BigDecimal.ZERO)

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should approve when customer age is equal to max age`() {
        val customer = Customer("", "", 30, "", BigDecimal.ZERO)

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should decline when customer age is greater than max age`() {
        val customer = Customer("", "", 35, "", BigDecimal.ZERO)

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.DECLINED, result)
    }
}