package com.challenge.itau.core.service.decisions

import CardDecisionService
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.service.decisions.conditions.CombinedEvaluator
import com.challenge.itau.core.service.decisions.conditions.MaxAgeEvaluator
import com.challenge.itau.core.service.decisions.conditions.MinAgeEvaluator
import com.challenge.itau.core.service.decisions.conditions.MinIncomeEvaluator
import com.challenge.itau.core.service.decisions.conditions.StateEvaluator
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal

/*
    * This file contains the definition of the PartnerCardService class, which implements the CardDecisionService interface
    * and provides the logic for deciding whether a customer is eligible for a partner card.
*/

@Service
class PartnerCardService(
        @Value("\${cards.parameters.partner-card.min-income}") private val minIncome: BigDecimal,
        @Value("\${cards.parameters.partner-card.min-age}") private val minAge: Int,
        @Value("\${cards.parameters.partner-card.combination.allowed-states}") private val allowedStates: List<String>,
        @Value("\${cards.parameters.partner-card.combination.max-age}") private val maxAge: Int,
) : CardDecisionService(CardType.PARTNER_CARD,
        listOf(
                MinIncomeEvaluator(minIncome),
                MinAgeEvaluator(minAge),
                CombinedEvaluator(listOf(StateEvaluator(allowedStates), MaxAgeEvaluator(maxAge)))
        ))