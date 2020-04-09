package com.scrobot.generator.controlles

import com.scrobot.generator.domain.IdGeneratorService
import com.scrobot.generator.entities.ResponseId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("api")
class IdGeneratorController(
    private val service: IdGeneratorService
) {

    @GetMapping("id")
    fun getNextId(): Mono<ResponseId> = service
        .generateNextId()
        .toMono()
        .map { ResponseId(it) }

}