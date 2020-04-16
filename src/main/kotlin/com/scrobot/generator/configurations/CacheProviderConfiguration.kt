package com.scrobot.generator.configurations

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit


@Configuration
class CacheProviderConfiguration(
    @Value("\${hot-cache.max-size}")
    private val maxCacheSize: Long
) {

    @Bean
    fun getCacheProvider(): Cache<Long, Boolean> = Caffeine.newBuilder()
        .maximumSize(maxCacheSize)
        .expireAfterWrite(1, TimeUnit.MINUTES)
        .build()

}