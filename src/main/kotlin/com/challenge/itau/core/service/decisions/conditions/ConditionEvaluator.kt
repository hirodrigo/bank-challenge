package com.challenge.itau.core.service.decisions.conditions

import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.Customer

/*
 * This interface defines a contract for condition evaluators.
 * Each evaluator will implement the evaluate method to determine the card status
 * based on the provided customer.
*/

fun interface ConditionEvaluator {
    fun evaluate(customer: Customer): CardStatus
}