package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class StateEvaluatorTest {

    private val deniedStates = listOf("SP", "RJ", "MG")
    private val evaluator = StateEvaluator(deniedStates)

    @Test
    fun `should decline when customer state is in denied states`() {
        val customer = Customer("", "", 30, "SP", BigDecimal("5000.00"))

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.DECLINED, result)
    }

    @Test
    fun `should approve when customer state is not in denied states`() {
        val customer = Customer("", "", 30, "BA", BigDecimal("5000.00"))

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }

    @Test
    fun `should approve when customer state is empty`() {
        val customer = Customer("", "", 30, "", BigDecimal("5000.00"))

        val result = evaluator.evaluate(customer)

        assertEquals(CardStatus.APPROVED, result)
    }
}