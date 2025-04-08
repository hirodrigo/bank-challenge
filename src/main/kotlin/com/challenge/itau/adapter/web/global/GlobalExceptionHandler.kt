package com.challenge.itau.adapter.web.global

import com.challenge.itau.adapter.web.global.dto.response.error.ErrorDetailResponse
import com.challenge.itau.adapter.web.global.dto.response.error.ErrorResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

/*
 * This class handles global exceptions for the application.
 * It provides methods to handle specific exceptions and return appropriate error responses.
 *
 * @property ex The exception that occurred during the request processing.
 * @property request The web request associated with the exception.
*/

@ControllerAdvice
class GlobalExceptionHandler {

    @Value("\${spring.application.name}")
    lateinit var applicationName: String


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
            ex: MethodArgumentNotValidException,
            request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val fieldErrors = ex.bindingResult.fieldErrors.map {
            FieldError(it.objectName, it.field, it.defaultMessage ?: "Invalid value")
        }
        val errorResponse = ErrorResponse(
                code = HttpStatus.BAD_REQUEST.value().toString(),
                message = HttpStatus.BAD_REQUEST.reasonPhrase,
                errorDetail = ErrorDetailResponse(
                        app = applicationName,
                        errorType = "Validation Error",
                        internalMessage = fieldErrors.joinToString(", ") { "${it.defaultMessage}" }
                )
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
            ex: HttpMessageNotReadableException,
            request: WebRequest
    ): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse(
                code = HttpStatus.BAD_REQUEST.value().toString(),
                message = HttpStatus.BAD_REQUEST.reasonPhrase,
                errorDetail = ErrorDetailResponse(
                        app = applicationName,
                        errorType = "JSON Parse Error",
                        internalMessage = "Invalid request body: ${ex.message}"
                )
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
            ex: Exception
    ): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value().toString(),
                message = "Um erro inesperado ocorreu.",
                errorDetail = ErrorDetailResponse(
                        app = applicationName,
                        errorType = "SERVICO_INDISPONIVEL",
                        internalMessage = "Tivemos um problema, mas fique tranquilo que nosso time já foi avisado."
                )
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}