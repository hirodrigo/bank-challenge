package com.desafio.itau.adapter.http.cards

import com.desafio.itau.adapter.http.cards.dto.request.CardsRequest
import com.desafio.itau.adapter.http.cards.dto.response.CustomerResponse

class CardsMapper {

    companion object {
        fun toCustomerResponse(cardsRequest: CardsRequest): CustomerResponse {
            return CustomerResponse(
                    name = cardsRequest.customer!!.name!!,
                    cpf = cardsRequest.customer!!.cpf!!,
                    age = cardsRequest.customer!!.age!!,
                    birthDate = cardsRequest.customer!!.birthDate!!,
                    state = cardsRequest.customer!!.state!!,
                    monthlyIncome = cardsRequest.customer!!.monthlyIncome!!,
                    email = cardsRequest.customer!!.email!!,
                    whatsappPhone = cardsRequest.customer!!.whatsappPhone!!
            )
        }
    }
}