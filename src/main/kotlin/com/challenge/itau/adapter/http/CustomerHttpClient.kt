package com.challenge.itau.adapter.http

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Component
@FeignClient(name = "customerClient", url = "\${customer.service.url}")
fun interface CustomerClient {

    @GetMapping("/clientes")
    fun getCustomer(@RequestParam("cpf") id: String): CustomerResponse
}

data class CustomerResponse(
    @JsonProperty("id_cliente") val id: String
)