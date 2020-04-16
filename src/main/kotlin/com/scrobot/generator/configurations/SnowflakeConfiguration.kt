package com.scrobot.generator.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("snowflake")
data class SnowflakeConfiguration(
    private val map: Map<String, Any>
) {
    val nodeIdBits: Int = map["node-id-bits"]?.toString()?.toInt() ?: 10
    val sequenceBits: Int = map["sequence-bits"]?.toString()?.toInt() ?: 12
    val customEpoch: Long = map["custom-epoch"]?.toString()?.toLong() ?: 1577836800000
}