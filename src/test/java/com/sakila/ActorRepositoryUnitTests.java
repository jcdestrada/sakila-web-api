package com.sakila;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sakila.repository.ActorRepository;

@SpringBootTest
class ActorRepositoryUnitTests {

	@Autowired
	private ActorRepository actorRepo;

	@Test
	public void test3findAllByIdActorIds() {
		Integer[] actorIds = { 1, 2, 3 };
		assert (actorRepo.findAllById(Arrays.asList(actorIds)).size() == actorIds.length);
	}

	@Test
	public void test2findAllByIdActorIds() {
		Integer[] actorIds = { 1, 2 };
		assert (actorRepo.findAllById(Arrays.asList(actorIds)).size() == actorIds.length);
	}

	@Test
	public void test1findAllByIdActorIds() {
		Integer[] actorIds = { 1 };
		assert (actorRepo.findAllById(Arrays.asList(actorIds)).size() == actorIds.length);
	}

	@Test
	public void testFindByName_NoResult() {
		String name = "RANDOM";
		assert (actorRepo.findByName(name).size() == 0);
	}

	@Test
	public void testFindByName_WithResult() {
		String name = "PENELOPE";
		assert (actorRepo.findByName(name).size() > 0);
	}
}
