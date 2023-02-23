package com.sakila;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasProperty;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sakila.exception.SakilaException;
import com.sakila.service.ActorService;
import com.sakila.view.ActorVO;

@SpringBootTest
class ActorServiceUnitTests {

	@Autowired
	private ActorService actorService;

	@Test
	public void test_retrieveAllActors_Normal() {
		assert (actorService.retrieveAllActors().size() > 1);
	}

	@Test
	public void test_retrieveActorId_Normal() {
		ActorVO viewObject = actorService.retrieveActorById(1);
		assertThat("ActorVO is not null", viewObject, is(notNullValue(ActorVO.class)));
		assertThat("Class is ActorVO", viewObject, isA(ActorVO.class));
		assertThat("ActorVO.getActorId is 1", viewObject.getActorId(), equalTo(1));
	}

	@Test
	public void test_retrieveActorId_IdDoesNotExist() {
		SakilaException se = null;
		try {
			actorService.retrieveActorById(1000);
		} catch (SakilaException e) {
			se = e;
		}
		assertThat("Exception is not null", se, is(notNullValue(SakilaException.class)));
		assertThat("SakilaException.ERROR_CODE is REPOSITORY_ERROR", se.getMessageCode(),
				equalTo(SakilaException.ERROR_CODE.REPOSITORY_ERROR));
		assertThat("SakilaException message is NO_DATA_FOUND_MSG", se.getMessage(),
				equalTo(SakilaException.NO_DATA_FOUND_MSG));
	}

	@Test
	public void test_retrieveActorByName_WithResult() {
		SakilaException se = null;
		List<ActorVO> voList = null;
		try {
			voList = actorService.retrieveActorByName("PENELOPE");
		} catch (SakilaException e) {
			se = e;
		}
		assertThat("SakilaException is nulll", se, is(nullValue()));
		assertThat("voList is not null", voList, is(notNullValue()));
		voList.stream().forEach(actor -> {
			assertThat("Result should have RANDOM", actor,
					anyOf(hasProperty("firstName", is("PENELOPE")), 
							hasProperty("lastName", is("PENELOPE"))));
		});
	}
}
