package com.scrobot.generator.cache.data

interface CacheRepository {

    fun putValue(id: Long)
    fun checkValueExists(id: Long): Boolean

}