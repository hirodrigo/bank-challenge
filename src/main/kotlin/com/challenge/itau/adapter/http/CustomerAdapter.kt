package com.challenge.itau.adapter.http

import com.challenge.itau.core.port.out.GetCustomerPortOut
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

/*
    * This class is responsible for fetching customer id from an external service.
 */

@Component
class CustomerAdapter(
    private val customerClient: CustomerClient
) : GetCustomerPortOut {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Cacheable("customerId")
    override fun getCustomerId(cpf: String): String {
        log.info("Fetching customer ID for customers CPF")

        try {
            val customerId = customerClient.getCustomer(cpf).id
            log.info("Customer ID fetched successfully: $customerId")
            return customerId
        } catch (e: Exception) {
            log.error("Error fetching customer ID for customers CPF", e)
            throw RuntimeException(e)
        }
    }
}