package com.challenge.itau.core.service.decisions

import CardDecisionService
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.service.decisions.conditions.MinAgeEvaluator
import com.challenge.itau.core.service.decisions.conditions.MinIncomeEvaluator
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal

/*
    * This file contains the definition of the CardWithCashback class, which implements the CardDecisionService interface
    * and provides the logic for deciding whether a customer is eligible for a card with cashback.
*/

@Service
class CardWithCashbackService(
        @Value("\${cards.parameters.card-with-cashback.min-income}") private val minIncome: BigDecimal,
        @Value("\${cards.parameters.card-with-cashback.min-age}") private val minAge: Int
) : CardDecisionService(
    CardType.CARD_WITH_CASHBACK,
    listOf(
        MinIncomeEvaluator(minIncome),
        MinAgeEvaluator(minAge)
    )
)