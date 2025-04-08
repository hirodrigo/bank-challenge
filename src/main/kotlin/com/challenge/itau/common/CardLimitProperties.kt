package com.challenge.itau.common

import com.challenge.itau.core.domain.CardType
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Configuration
class CardLimitProperties {

    @Value("\${cards.parameters.card-without-annual-fee.available-limit:1000.00}")
    lateinit var cardWithoutAnnualFeeAvailableLimit: BigDecimal

    @Value("\${cards.parameters.partner-card.available-limit:3000.00}")
    lateinit var partnerCardAvailableLimit: BigDecimal

    @Value("\${cards.parameters.card-with-cashback.available-limit:5000.00}")
    lateinit var cardWithCashbackAvailableLimit: BigDecimal
}

@Component
class CardTypeInitializer(private val cardLimitProperties: CardLimitProperties) {

    @PostConstruct
    fun initializeCardLimits() {
        CardType.initializeLimits(cardLimitProperties)
    }
}

