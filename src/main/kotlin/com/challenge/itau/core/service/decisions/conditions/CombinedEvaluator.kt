package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer
import org.slf4j.LoggerFactory

/*
    * This class combines multiple condition evaluators to determine the card status.
    * It evaluates the customer against all evaluators and returns DECLINED if all evaluators
    * returns DECLINED.
    * If any evaluator return APPROVED, it returns APPROVED.
    *
    * DECLINED and DECLINED = DECLINED;
    * APPROVED and APPROVED = APPROVED;
    * DECLINED and APPROVED = APPROVED;
    * APPROVED and DECLINED = APPROVED;
 */


class CombinedEvaluator(private val evaluators: List<ConditionEvaluator>) : ConditionEvaluator {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun evaluate(customer: Customer): CardStatus {
        log.info("Evaluating combined conditions for customer: $customer")
        val statuses = evaluators.map { it.evaluate(customer) }

        if (statuses.all { it == CardStatus.DECLINED }) {
            return CardStatus.DECLINED
        }
        return CardStatus.APPROVED
    }
}