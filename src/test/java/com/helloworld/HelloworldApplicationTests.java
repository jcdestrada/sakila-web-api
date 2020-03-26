package com.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.helloworld.controller.ActorController;
import com.helloworld.controller.HelloWorldController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloworldApplicationTests {

	@Autowired
	private ActorController actorController;

	@Autowired
	private HelloWorldController helloWorldController;
	
	@Test
	void contextLoads() {
		assertThat(actorController).isNotNull();
		assertThat(helloWorldController).isNotNull();
	}

}
