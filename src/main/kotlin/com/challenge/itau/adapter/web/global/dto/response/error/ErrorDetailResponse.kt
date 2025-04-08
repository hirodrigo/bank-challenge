package com.challenge.itau.adapter.web.global.dto.response.error

import com.fasterxml.jackson.annotation.JsonProperty

/*
 * This class represents the detailed information about an error response.
 * It contains the application name, error type, and internal message.
 *
 * @property app The name of the application where the error occurred.
 * @property errorType The type of error that occurred.
 * @property internalMessage A detailed internal message about the error.
*/

data class ErrorDetailResponse(

        @JsonProperty("app")
        val app: String,

        @JsonProperty("tipo_erro")
        val errorType: String,

        @JsonProperty("mensagem_interna")
        val internalMessage: String
)