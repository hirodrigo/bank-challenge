package com.challenge.itau.adapter.http

import com.challenge.itau.core.port.out.GetCustomerPortOut
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CustomerAdapterTest {

    private val customerClient: CustomerClient = mock(CustomerClient::class.java)
    private val customerAdapter: GetCustomerPortOut = CustomerAdapter(customerClient)

    @Test
    fun `should fetch customer ID successfully`() {
        // Arrange
        val cpf = "12345678900"
        val expectedCustomerId = "123"
        `when`(customerClient.getCustomer(cpf)).thenReturn(CustomerResponse(expectedCustomerId))

        // Act
        val customerId = customerAdapter.getCustomerId(cpf)

        // Assert
        assertEquals(expectedCustomerId, customerId)
        verify(customerClient, times(1)).getCustomer(cpf)
    }

    @Test
    fun `should throw exception when customer client fails`() {
        // Arrange
        val cpf = "12345678900"
        `when`(customerClient.getCustomer(cpf)).thenThrow(RuntimeException("Service unavailable"))

        // Act & Assert
        val exception = assertThrows<RuntimeException> {
            customerAdapter.getCustomerId(cpf)
        }
        assertEquals("java.lang.RuntimeException: Service unavailable", exception.message)
        verify(customerClient, times(1)).getCustomer(cpf)
    }
}