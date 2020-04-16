package com.scrobot.generator.cache.data

import com.github.benmanes.caffeine.cache.Cache
import org.springframework.stereotype.Component

@Component
class CacheRepositoryImpl(
    private val inMemoryCache: Cache<Long, Boolean>
) : CacheRepository {

    override fun putValue(id: Long) {
        inMemoryCache.put(id, true)
    }

    override fun checkValueExists(id: Long): Boolean = inMemoryCache.getIfPresent(id) ?: false
}