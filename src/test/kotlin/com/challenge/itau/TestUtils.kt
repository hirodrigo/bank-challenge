package com.challenge.itau

import com.challenge.itau.adapter.web.cards.dto.request.CardsRequest
import com.challenge.itau.adapter.web.cards.dto.request.CustomerRequest
import com.challenge.itau.core.domain.Card
import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.domain.Customer
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

class TestUtils {
    companion object {
        private val customerRequest = CustomerRequest(
            name = "João",
            cpf = "123.456.789-00",
            age = 30,
            birthDate = LocalDate.of(2002, 9, 8),
            state = "SP",
            monthlyIncome = BigDecimal("5000.00"),
            email = "joao@exemplo.com",
            whatsappPhone = "11999999999"
        )

        val cardRequest = CardsRequest(
            customer = customerRequest
        )

        val cardList = listOf(
            Card(
                cardType = CardType.CARD_WITHOUT_ANNUAL_FEE,
                monthlyFee = BigDecimal("0.00"),
                availableLimit = BigDecimal("5000.00"),
                status = CardStatus.APPROVED
            )
        )

        val customer = Customer(
            id = UUID.randomUUID().toString(),
            cpf = "123.456.789-00",
            age = 30,
            state = "SP",
            monthlyIncome = BigDecimal("5000.00"),
        )

        val customerLowIncome = Customer(
            id = UUID.randomUUID().toString(),
            cpf = "123.456.789-00",
            age = 30,
            state = "SP",
            monthlyIncome = BigDecimal("999.00"),
        )
    }
}