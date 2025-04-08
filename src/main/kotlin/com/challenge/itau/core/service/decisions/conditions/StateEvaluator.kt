package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.slf4j.LoggerFactory

/*
 * This class evaluates the state condition for a customer.
 * If the customer's state is in the list of denied states, the card status is declined.
 * Otherwise, it is approved.
*/

class StateEvaluator (private val deniedStates: List<String>): ConditionEvaluator {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun evaluate(customer: Customer): CardStatus {
        log.info("Evaluating state condition for customer: $customer, denied states: $deniedStates")
        return if (deniedStates.contains(customer.state)) CardStatus.DECLINED else CardStatus.APPROVED
    }
}