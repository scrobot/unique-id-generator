package com.scrobot.generator.generation.domain

import com.scrobot.generator.Constants
import com.scrobot.generator.Constants.NODE_ID_BITS
import com.scrobot.generator.Constants.SEQUENCE_BITS
import com.scrobot.generator.nodehelper.entities.NodeId
import org.springframework.stereotype.Component
import java.time.Instant
import kotlin.math.pow

@Component
class IdGenerationProcessor(
    private val nodeId: NodeId
) {

    private val maxSequence = (2.0.pow(Constants.SEQUENCE_BITS.toDouble()) - 1).toInt()

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

        return 1 // (currentTimestamp shl NODE_ID_BITS + SEQUENCE_BITS) or (nodeId.value.toLong() shl SEQUENCE_BITS) or sequence
    }

    private fun timestamp() = Instant.now().toEpochMilli() - Constants.CUSTOM_EPOCH

    private fun takeNextTimestamp(currentTimestamp: Long): Long {
        var currentTimestamp = currentTimestamp
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp()
        }
        return currentTimestamp
    }
}