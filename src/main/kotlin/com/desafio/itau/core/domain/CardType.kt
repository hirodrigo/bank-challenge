package com.desafio.itau.core.domain

import java.math.BigDecimal


/* This enum is used to define the different types of cards available in the system.
 * Each card type has an associated available limit and required monthly income.
 *
 * @property availableLimit The available limit for the card type.
 * @property requiredIncome The required monthly income for the card type.
 */
enum class CardType(val availableLimit: BigDecimal, val requiredIncome: BigDecimal) {

    CARTAO_SEM_ANUIDADE(BigDecimal.valueOf(1000), BigDecimal.valueOf(3500)),
    CARTAO_DE_PARCEIROS(BigDecimal.valueOf(3000), BigDecimal.valueOf(5500)),
    CARTAO_COM_CASHBACK(BigDecimal.valueOf(5000), BigDecimal.valueOf(7500));
}