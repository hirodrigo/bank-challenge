package com.challenge.itau.adapter.web.global.dto.response.error

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/*
 * This class represents the output data for an error response.
 * It contains the error code, message, and detailed information about the error.
 *
 * @property code The error code associated with the response.
 * @property message A descriptive message about the error.
 * @property errorDetail Detailed information about the error.
*/

@Schema(description = "Represents the output data for an error response.")
data class ErrorResponse(

        @Schema(description = "The error code associated with the response.", example = "400")
        @JsonProperty("codigo")
        val code: String,

        @Schema(description = "A descriptive message about the error.", example = "Invalid input data.")
        @JsonProperty("mensagem")
        val message: String,

        @Schema(description = "Detailed information about the error.")
        @JsonProperty("detalhe_erro")
        val errorDetail: ErrorDetailResponse
)