package com.scrobot.generator.cache.domain

import com.scrobot.generator.cache.exceptions.IdNotUniqueException

interface IdCacheService {

    @Throws(IdNotUniqueException::class)
    fun checkIdUniqueOrThrow(id: Long)

}