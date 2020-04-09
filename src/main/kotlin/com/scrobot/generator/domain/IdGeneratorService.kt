package com.scrobot.generator.domain

import reactor.core.publisher.Mono

interface IdGeneratorService {

    fun generateNextId(): Long

}