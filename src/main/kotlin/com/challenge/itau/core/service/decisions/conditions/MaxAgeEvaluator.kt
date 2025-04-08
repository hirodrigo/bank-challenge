package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.slf4j.LoggerFactory

/*
 * This class evaluates the maximum age condition for a customer.
 * If the customer's age is less than or equal to the specified maximum age, the card status is approved.
 * Otherwise, it is declined.
*/

class MaxAgeEvaluator(private val maxAge: Int) : ConditionEvaluator {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun evaluate(customer: Customer): CardStatus {
        log.info("Evaluating max age condition for customer: $customer, max age: $maxAge")
        return if (customer.age <= maxAge) CardStatus.APPROVED else CardStatus.DECLINED
    }
}




