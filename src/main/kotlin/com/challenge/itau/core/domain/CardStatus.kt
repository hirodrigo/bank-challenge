package com.challenge.itau.core.domain

/*
 * This file defines the CardStatus enum class, which represents the status of a card.
 * The statuses are APPROVED and REPROVED, each with a localized string representation.
 * The localized strings are in Portuguese, as indicated by the values "APROVADO" and "REPROVADO".
*/

enum class CardStatus(val localizedStatus: String) {

    APPROVED("APROVADO"),
    DECLINED("REPROVADO")
}