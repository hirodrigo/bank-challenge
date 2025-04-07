package com.desafio.itau.adapter.http.global

import com.desafio.itau.adapter.http.global.dto.response.error.ErrorDetailResponse
import com.desafio.itau.adapter.http.global.dto.response.error.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

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
                        app = "Desafio Itau",
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
                        app = "Desafio Itau",
                        errorType = "JSON Parse Error",
                        internalMessage = "Invalid request body: ${ex.message}"
                )
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}