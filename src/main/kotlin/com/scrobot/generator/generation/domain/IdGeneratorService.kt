package com.scrobot.generator.generation.domain

import reactor.core.publisher.Mono

interface IdGeneratorService {

    fun generateNextId(): Mono<Long>

}