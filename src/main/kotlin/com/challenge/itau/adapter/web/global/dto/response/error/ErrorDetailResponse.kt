package com.challenge.itau.adapter.web.global.dto.response.error

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/*
 * This class represents the detailed information about an error response.
 * It contains the application name, error type, and internal message.
*/

@Schema(description = "Detailed information about an error response.")
data class ErrorDetailResponse(

        @Schema(description = "The name of the application where the error occurred.", example = "MyApp")
        @JsonProperty("app")
        val app: String,

        @Schema(description = "The type of error that occurred.", example = "VALIDATION_ERROR")
        @JsonProperty("tipo_erro")
        val errorType: String,

        @Schema(description = "A detailed internal message about the error.", example = "Field 'name' cannot be empty.")
        @JsonProperty("mensagem_interna")
        val internalMessage: String
)