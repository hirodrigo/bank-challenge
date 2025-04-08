package com.challenge.itau.core.service

import CardDecisionService
import com.challenge.itau.core.domain.Card
import com.challenge.itau.core.domain.Customer
import com.challenge.itau.core.port.`in`.CardRequestPortIn
import com.challenge.itau.core.port.out.GetCustomerPortOut
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/*
    * This service is responsible for creating card requests based on customer information.
    * It uses a list of card decision services to determine the appropriate cards for the customer.
    *
    * @param cardDecisions A list of card decision services used to evaluate the customer's eligibility for cards.
*/

@Service
class CardRequestService(
        private val cardDecisions: List<CardDecisionService>,
        private val getCustomerPortOut: GetCustomerPortOut
) : CardRequestPortIn {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun createCardRequest(customer: Customer): List<Card> = runBlocking {
        val cardList = mutableListOf<Card>()
        customer.id = getCustomerPortOut.getCustomerId(customer.cpf)

        coroutineScope {
            val deferredCards = cardDecisions.map { decision ->
                async {
                    val card = decision.decideCard(customer)
                    if (card.status == com.challenge.itau.core.domain.CardStatus.APPROVED) {
                        cardList.add(card)
                    }
                }
            }
            deferredCards.forEach { it.await() }
        }

        return@runBlocking cardList
    }
}