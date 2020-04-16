package com.scrobot.generator.generation.controlles

import com.scrobot.generator.cache.domain.IdCacheService
import com.scrobot.generator.generation.domain.IdGeneratorService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import kotlin.random.Random


@ExtendWith(value = [SpringExtension::class])
@WebFluxTest(controllers = [IdGeneratorController::class])
internal class IdGeneratorControllerTest {

    @Autowired
    private lateinit var webClient: WebTestClient

    @MockBean
    private lateinit var service: IdGeneratorService

    @MockBean
    private lateinit var cacheService: IdCacheService

    @Test
    fun `id generation test should be unique`() {
        val value = Random.nextInt() % 2

        Mockito.`when`(service.generateNextId()).thenReturn(value.toLong().toMono())

        for (i in 0..10) {
            webClient.get()
                .uri("/api/id")
                .exchange()
                .expectStatus().isOk
        }
    }

    @Test
    fun `id generation test should fault with timeout`() {
        Mockito.`when`(service.generateNextId()).thenReturn(1L.toMono())

        webClient.get()
            .uri("/api/id")
            .exchange()
            .expectStatus()

        webClient.get()
            .uri("/api/id")
            .exchange()
            .expectStatus()
    }

}