package com.challenge.itau.core.domain

import com.challenge.itau.common.CardLimitProperties
import java.math.BigDecimal


/*
    * Enum class that is used to define the types of cards that can be offered to customers.
*/

enum class CardType(val localizedName: String) {
    CARD_WITHOUT_ANNUAL_FEE("CARTAO_SEM_ANUIDADE"),
    PARTNER_CARD("CARTAO_DE_PARCEIROS"),
    CARD_WITH_CASHBACK("CARTAO_COM_CASHBACK");

    var availableLimit: BigDecimal = BigDecimal.ZERO

    companion object {
        fun initializeLimits(properties: CardLimitProperties) {
            CARD_WITHOUT_ANNUAL_FEE.availableLimit = properties.cardWithoutAnnualFeeAvailableLimit
            PARTNER_CARD.availableLimit = properties.partnerCardAvailableLimit
            CARD_WITH_CASHBACK.availableLimit = properties.cardWithCashbackAvailableLimit
        }
    }
}