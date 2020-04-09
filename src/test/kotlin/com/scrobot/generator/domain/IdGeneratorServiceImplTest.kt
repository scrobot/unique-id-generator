package com.scrobot.generator.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class IdGeneratorServiceImplTest {

    @Autowired
    private lateinit var service: IdGeneratorService

    @Test
    fun `test unique id generator`() {
        val size = 1_000
        val ids = mutableListOf<Long>()

        for (i in 0 until size) {
            val nextId = service.generateNextId()
            ids.add(nextId)
        }

        assertEquals(ids.distinct().size, size)
    }

}