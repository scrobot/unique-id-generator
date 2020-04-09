package com.scrobot.generator.domain

import org.springframework.cloud.client.discovery.DiscoveryClient

class IdGeneratorServiceImpl(
    private val discoveryClient: DiscoveryClient
) : IdGeneratorService {

    init {

    }

}