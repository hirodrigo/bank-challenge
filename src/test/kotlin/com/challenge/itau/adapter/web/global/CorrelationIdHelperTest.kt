package com.challenge.itau.adapter.web.global

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.slf4j.MDC
import kotlin.test.assertEquals

class CorrelationIdHelperTest {

    private val filter = TestableCorrelationIdHelper()
    private val request = mock(HttpServletRequest::class.java)
    private val response = mock(HttpServletResponse::class.java)
    private val filterChain = mock(FilterChain::class.java)

    @Test
    fun `should add correlation ID to response when header is not present`() {
        `when`(request.getHeader(CorrelationIdHelper.CORRELATION_ID_HEADER)).thenReturn(null)

        filter.doFilterInternal(request, response, filterChain)

        verify(response).setHeader(eq(CorrelationIdHelper.CORRELATION_ID_HEADER), anyString())
        verify(filterChain).doFilter(request, response)
        assertEquals(null, MDC.get(CorrelationIdHelper.CORRELATION_ID_HEADER))
    }

    @Test
    fun `should use existing correlation ID from request header`() {
        val existingCorrelationId = "existing-correlation-id"
        `when`(request.getHeader(CorrelationIdHelper.CORRELATION_ID_HEADER)).thenReturn(existingCorrelationId)

        filter.doFilterInternal(request, response, filterChain)

        verify(response).setHeader(CorrelationIdHelper.CORRELATION_ID_HEADER, existingCorrelationId)
        verify(filterChain).doFilter(request, response)
        assertEquals(null, MDC.get(CorrelationIdHelper.CORRELATION_ID_HEADER))
    }
}

class TestableCorrelationIdHelper : CorrelationIdHelper() {
    public override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        super.doFilterInternal(request, response, filterChain)
    }
}