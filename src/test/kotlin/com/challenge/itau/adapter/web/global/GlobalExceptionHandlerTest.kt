package com.challenge.itau.adapter.web.global

import com.challenge.itau.adapter.web.cards.CardsController
import com.challenge.itau.core.port.`in`.CardRequestPortIn
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GlobalExceptionHandler::class, CardsController::class)
class GlobalExceptionHandlerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @TestConfiguration
    class MockConfig {
        @Bean
        fun cardRequestPortIn(): CardRequestPortIn = Mockito.mock(CardRequestPortIn::class.java)
    }

    @Test
    fun `should handle MethodArgumentNotValidException`() {
        val invalidRequest = """
            {
                "nome": "",
                "cpf": "123",
                "idade": 17,
                "data_nascimento": "2005-01-01",
                "uf": "",
                "renda_mensal": -1000,
                "email": "",
                "telefone_whatsapp": ""
            }
        """.trimIndent()

        val result = mockMvc.perform(
            post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest)
        )
            .andExpect(status().isBadRequest)
            .andReturn()

        val response = result.response.contentAsString
        assert(response.contains("Validation Error"))
    }

    @Test
    fun `should handle HttpMessageNotReadableException`() {
        val invalidJson = """
            {
                cliente : {
                    "nome": "",
                    "cpf": "12345678900",
                    "idade": "idade-invalida",
                    "data_nascimento": "2005-01-01",
                    "uf": "",
                    "renda_mensal": -1000,
                    "email": "",
                    "telefone_whatsapp": ""
                },
            }
        """.trimIndent()

        val result = mockMvc.perform(
            post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson)
        )
            .andExpect(status().isBadRequest)
            .andReturn()

        val response = result.response.contentAsString
        assert(response.contains("JSON Parse Error"))
    }

    @Test
    fun `should handle generic Exception`() {
        val result = mockMvc.perform(
            post("/some-endpoint") // Replace with the actual endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        )
            .andExpect(status().isInternalServerError)
            .andReturn()

        val response = result.response.contentAsString
        assert(response.contains("Um erro inesperado ocorreu."))
    }
}