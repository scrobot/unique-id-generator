package com.scrobot.generator.cache.domain

import com.scrobot.generator.cache.data.CacheRepository
import com.scrobot.generator.cache.exceptions.IdNotUniqueException
import com.scrobot.generator.nodehelper.NodeIdResolver
import org.springframework.stereotype.Service

@Service
class IdCacheServiceImpl(
    private val repository: CacheRepository,
    private val nodeIdResolver: NodeIdResolver
) : IdCacheService {

    override fun checkIdUniqueOrThrow(id: Long) {
        when (repository.checkValueExists(id)) {
            true -> throw IdNotUniqueException(id, nodeIdResolver.instanceId)
            else -> repository.putValue(id)
        }
    }
}