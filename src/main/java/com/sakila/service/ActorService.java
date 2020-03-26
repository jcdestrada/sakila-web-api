package com.sakila.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sakila.model.Actor;
import com.sakila.repository.ActorRepository;
import com.sakila.view.ActorVO;

@Service
public class ActorService {
	@Autowired
	private ActorRepository actorRepo;
	
	public String helloWorld() {
		return "Hello World";
	}

	public List<ActorVO> retrieveAllActors() {
		List<Actor> entityList = actorRepo.findAll();
		List<ActorVO> voList = new ArrayList<ActorVO>();
		entityList.stream().forEach(actor -> {
			ActorVO actorVO = new ActorVO();
			BeanUtils.copyProperties(actor, actorVO);
			voList.add(actorVO);
		});
		return voList;
	}

	public ActorVO retrieveActorById(Integer actorId) {
		ActorVO actorVOResponse = new ActorVO();
		if (actorId != null && actorId > 0) {
			actorRepo.findById(actorId).ifPresent(actor -> BeanUtils.copyProperties(actor, actorVOResponse));
		}
		return actorVOResponse;
	}

	public List<ActorVO> retrieveActorByName(String name) {
		List<ActorVO> voList = new ArrayList<ActorVO>();
		if (!StringUtils.isEmpty(name)) {
			actorRepo.findByName(name).forEach(actor -> {
				ActorVO actorVO = new ActorVO();
				BeanUtils.copyProperties(actor, actorVO);
				voList.add(actorVO);
			});
		}
		return voList;
	}

	@Transactional
	public ActorVO addActor(ActorVO actorVORequest) {
		Actor entity = new Actor();
		BeanUtils.copyProperties(actorVORequest, entity);
		entity.setActorId(null);
		entity.setLastUpdate(ZonedDateTime.now());
		entity = actorRepo.save(entity);
		ActorVO actorVOResponse = new ActorVO();
		BeanUtils.copyProperties(entity, actorVOResponse);
		return actorVOResponse;
	}

	@Transactional
	public void removeActor(Integer actorId) {
		try {
			actorRepo.deleteById(actorId);
		} catch (DataAccessException dae) {
			dae.getMessage();
			dae.printStackTrace();
			throw dae;
		}
	}
}
