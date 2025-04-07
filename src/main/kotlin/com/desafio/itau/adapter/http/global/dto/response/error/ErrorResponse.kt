package com.desafio.itau.adapter.http.global.dto.response.error

import com.fasterxml.jackson.annotation.JsonProperty

/*
 * This class represents the output data for an error response.
 * It contains the error code, message, and detailed information about the error.
 *
 * @property code The error code associated with the response.
 * @property message A descriptive message about the error.
 * @property errorDetail Detailed information about the error.
*/

data class ErrorResponse(

        @JsonProperty("codigo")
        val code: String,

        @JsonProperty("mensagem")
        val message: String,

        @JsonProperty("detalhe_erro")
        val errorDetail: ErrorDetailResponse
)