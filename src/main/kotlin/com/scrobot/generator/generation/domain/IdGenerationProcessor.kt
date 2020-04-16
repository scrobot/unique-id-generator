package com.scrobot.generator.generation.domain

import com.scrobot.generator.configurations.SnowflakeConfiguration
import com.scrobot.generator.nodehelper.entities.NodeId
import org.springframework.stereotype.Component
import java.time.Instant
import kotlin.math.pow

@Component
class IdGenerationProcessor(
    private val config: SnowflakeConfiguration,
    private val nodeId: NodeId
) {

    private val maxSequence = (2.0.pow(config.sequenceBits.toDouble()) - 1).toInt()

    private var lastTimestamp = -1L
    private var sequence = 0L

    fun generateNextId(): Long {
        var currentTimestamp = timestamp()

        check(currentTimestamp >= lastTimestamp) { "Invalid System Clock!" }

        when(currentTimestamp) {
            lastTimestamp -> {
                sequence = sequence + 1 and maxSequence.toLong()
                if (sequence == 0L) { // Sequence Exhausted, wait till next millisecond.
                    currentTimestamp = takeNextTimestamp(currentTimestamp)
                }
            }
            else -> sequence = 0
        }

        lastTimestamp = currentTimestamp

        return (currentTimestamp shl config.nodeIdBits + config.sequenceBits) or (nodeId.value.toLong() shl config.sequenceBits) or sequence
    }

    private fun timestamp() = Instant.now().toEpochMilli() - config.customEpoch

    private fun takeNextTimestamp(currentTimestamp: Long): Long {
        var currentTimestamp = currentTimestamp
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp()
        }
        return currentTimestamp
    }
}