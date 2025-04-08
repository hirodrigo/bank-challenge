package com.challenge.itau.core.domain

import java.math.BigDecimal


/*
    * Enum class that is used to define the types of cards that can be offered to customers.
*/

enum class CardType(val localizedName: String, val availableLimit: BigDecimal) {

    CARD_WITHOUT_ANNUAL_FEE("CARTAO_SEM_ANUIDADE", BigDecimal.valueOf(1000)),
    PARTNER_CARD("CARTAO_DE_PARCEIROS", BigDecimal.valueOf(3000)),
    CARD_WITH_CASHBACK("CARTAO_COM_CASHBACK", BigDecimal.valueOf(5000));
}