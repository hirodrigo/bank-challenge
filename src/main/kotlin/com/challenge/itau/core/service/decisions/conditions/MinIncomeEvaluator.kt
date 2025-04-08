package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.slf4j.LoggerFactory
import java.math.BigDecimal

/*
 * This class evaluates the minimum income condition for a customer.
 * If the customer's monthly income is greater than or equal to the specified minimum income, the card status is approved.
 * Otherwise, it is declined.
*/

class MinIncomeEvaluator(private val minIncome: BigDecimal): ConditionEvaluator {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun evaluate(customer: Customer): CardStatus {
        log.info("Evaluating min income condition for customer: $customer, min income: $minIncome")
        return if (customer.monthlyIncome >= minIncome) CardStatus.APPROVED else CardStatus.DECLINED
    }
}