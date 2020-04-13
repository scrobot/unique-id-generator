package com.scrobot.generator

import com.scrobot.generator.generation.controlles.IdGeneratorController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UidgeneratorApplicationTests {

	@Autowired
	lateinit var controller: IdGeneratorController

	@Test
	fun contextLoads() {
		assertThat(controller).isNotNull;
	}

}
