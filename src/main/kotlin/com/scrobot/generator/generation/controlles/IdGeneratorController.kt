package com.scrobot.generator.generation.controlles

import com.scrobot.generator.generation.domain.IdGeneratorService
import com.scrobot.generator.generation.entities.ResponseId
import com.scrobot.generator.generation.exceptions.TimeoutGenerationException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.extra.retry.retryExponentialBackoff
import java.time.Duration

@RestController
@RequestMapping("api")
class IdGeneratorController(
    private val service: IdGeneratorService
) {

    @GetMapping("id")
    fun getNextId(): Mono<ResponseId> = service
        .generateNextId()
        .retryExponentialBackoff(5, Duration.ofSeconds(1))
        .map { ResponseId(it) }
        .timeout(Duration.ofSeconds(10), Mono.error(TimeoutGenerationException))

}