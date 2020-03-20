package com.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helloworld.service.ActorService;
import com.helloworld.vo.ActorVO;

@RestController
@RequestMapping("/actor")
public class ActorController {
	@Autowired
	private ActorService actorService;

	@RequestMapping("/all")
	public @ResponseBody List<ActorVO> retrieveAllActors() {
		return actorService.retrieveAllActors();
	}

	@RequestMapping("/id/{actorId}")
	public @ResponseBody ActorVO retrieveActorById(@PathVariable Integer actorId) {
		return actorService.retrieveActorById(actorId);
	}

	@RequestMapping("/search/name/{name}")
	public @ResponseBody List<ActorVO> retrieveActorById(@PathVariable String name) {
		return actorService.retrieveActorByName(name);
	}
}
