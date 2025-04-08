package com.challenge.itau.adapter.web.global

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

/*
 * This class is responsible for managing the correlation ID in HTTP requests and responses.
 * It ensures that each request has a unique correlation ID, which is used for tracking and logging purposes.
 *
 * @property CORRELATION_ID_HEADER The name of the header used to store the correlation ID.
*/

@Component
class CorrelationIdHelper: OncePerRequestFilter() {

    companion object {
        const val CORRELATION_ID_HEADER = "X-Correlation-Id"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val correlationId = request.getHeader(CORRELATION_ID_HEADER) ?: UUID.randomUUID().toString()
        MDC.put(CORRELATION_ID_HEADER, correlationId)

        try {
            response.setHeader(CORRELATION_ID_HEADER, correlationId)
            filterChain.doFilter(request, response)
        } finally {
            MDC.remove(CORRELATION_ID_HEADER)
        }
    }
}