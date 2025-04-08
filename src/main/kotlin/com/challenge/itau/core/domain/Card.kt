package com.challenge.itau.core.domain

import java.math.BigDecimal

/*
 * This file contains the definition of the Card data class, which represents a credit card
 * with its associated properties such as card type, monthly fee, available limit, and status.
 *
 * @property cardType The type of the card (e.g., CARD_WITHOUT_ANNUAL_FEE, PARTNER_CARD).
 * @property monthlyFee The monthly fee associated with the card.
 * @property availableLimit The available credit limit on the card.
 * @property status The current status of the card (e.g., active, inactive).
*/

data class Card(

        val cardType: CardType,
        val monthlyFee: BigDecimal,
        val availableLimit: BigDecimal,
        var status: CardStatus,
)