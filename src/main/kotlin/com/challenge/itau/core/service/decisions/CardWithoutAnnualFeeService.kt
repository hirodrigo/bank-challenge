package com.challenge.itau.core.service.decisions

import CardDecisionService
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.service.decisions.conditions.MinIncomeEvaluator
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal

/*
    * This file contains the definition of the CardWithoutAnnualFeeService class, which implements the CardDecisionService interface
    * and provides the logic for deciding whether a customer is eligible for a card without an annual fee.
*/

@Service
class CardWithoutAnnualFeeService(
        @Value("\${cards.parameters.partner-card.min-income}") private val minIncome: BigDecimal,
) : CardDecisionService(
    CardType.CARD_WITHOUT_ANNUAL_FEE,
    listOf(
        MinIncomeEvaluator(minIncome)
    )
)