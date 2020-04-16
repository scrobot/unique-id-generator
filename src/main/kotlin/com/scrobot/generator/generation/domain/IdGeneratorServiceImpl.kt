package com.scrobot.generator.generation.domain

import com.scrobot.generator.cache.domain.IdCacheService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


@Service
class IdGeneratorServiceImpl(
    private val processor: IdGenerationProcessor,
    private val cacheService: IdCacheService
) : IdGeneratorService {

    private val logger: Logger = LoggerFactory.getLogger("IdGeneratorService")

    override fun generateNextId(): Mono<Long> = processor.generateNextId()
        .toMono()
        .doOnNext {
            cacheService.checkIdUniqueOrThrow(it)
        }
        .doOnError {
            logger.error(it.localizedMessage, it)
        }

}