package com.challenge.itau.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager

@SpringBootTest(classes = [CacheConfig::class])
class CacheConfigTest {

    @Autowired
    private lateinit var cacheManager: CacheManager

    @Test
    fun `should create a CacheManager bean with expected configuration`() {
        assertThat(cacheManager).isNotNull
        val cache = cacheManager.getCache("testCache")
        assertThat(cache).isNotNull
    }
}