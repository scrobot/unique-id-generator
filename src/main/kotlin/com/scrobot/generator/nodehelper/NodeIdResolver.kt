package com.scrobot.generator.nodehelper

import com.scrobot.generator.Constants.NODE_ID_BITS
import com.scrobot.generator.nodehelper.entities.NodeId
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import kotlin.math.pow
import kotlin.random.Random

@Component
class NodeIdResolver(
    @Value("\${spring.cloud.consul.discovery.instanceId}")
    val instanceId: String?
) {

    private val maxNodeId = (2.0.pow(NODE_ID_BITS) - 1).toInt()

    fun extractNodeId(): Int? = instanceId?.let { id -> id.hashCode() }

    @Bean
    fun provideNodeId(): NodeId = NodeId((extractNodeId() ?: Random.nextInt()) and maxNodeId)

}