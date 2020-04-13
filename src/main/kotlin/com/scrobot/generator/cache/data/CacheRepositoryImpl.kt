package com.scrobot.generator.cache.data

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CacheRepositoryImpl : CacheRepository {

    private val inMemoryCache = ConcurrentHashMap<Long, Boolean>()

    override fun putValue(id: Long) {
        inMemoryCache[id] = true;
    }

    override fun checkValueExists(id: Long): Boolean = inMemoryCache[id] != null
}