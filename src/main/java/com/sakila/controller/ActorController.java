package com.sakila.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.exception.SakilaException;
import com.sakila.service.ActorService;
import com.sakila.view.ActorVO;

@RestController
@RequestMapping("/api/actor")
public class ActorController {
	@Autowired
	private ActorService actorService;

	@GetMapping("/helloworld")
	public @ResponseBody String helloWorld() {
		return actorService.helloWorld();
	}

	@GetMapping("/all")
	public @ResponseBody List<ActorVO> retrieveAllActors() throws SakilaException {
		return actorService.retrieveAllActors();

	}

	@GetMapping("/id/{actorId}")
	public @ResponseBody ActorVO retrieveActorById(@PathVariable Integer actorId) throws SakilaException {
		return actorService.retrieveActorById(actorId);

	}

	@GetMapping("/search/{name}")
	public @ResponseBody List<ActorVO> retrieveActorById(@PathVariable String name) throws SakilaException {
		return actorService.retrieveActorByName(name);
	}

	@PostMapping("/add")
	public ResponseEntity<ActorVO> addActor(@Valid @RequestBody ActorVO actorVO) {
		ResponseEntity<ActorVO> response = null;
		try {
			ActorVO actorVOResponse = actorService.addActor(actorVO);
			response = ResponseEntity.ok(actorVOResponse);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<ActorVO>(actorVO, HttpStatus.EXPECTATION_FAILED);
		}
		return response;
	}

	@PostMapping("/remove/{actorId}")
	public ResponseEntity<String> removeActor(@PathVariable @NotNull Integer actorId) {
		ResponseEntity<String> response = null;
		try {
			actorService.removeActor(actorId);
			response = ResponseEntity.ok(String.format("Actor %s has been deleted successfully", actorId));
		} catch (DataAccessException e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(String.format("Unable to delete Actor %s", actorId),
					HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
