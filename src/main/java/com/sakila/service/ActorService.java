package com.sakila.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sakila.exception.SakilaException;
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

	public List<ActorVO> retrieveAllActors() throws SakilaException{
		List<ActorVO> voList = null;
		
		voList = actorRepo.findAll().stream()
				.map(ActorVO::new)
				.collect(Collectors.toList());
		if (voList == null || voList.size() == 0) {
			throw new SakilaException(SakilaException.ERROR_CODE.REPOSITORY_ERROR, SakilaException.NO_DATA_FOUND_MSG);
		}
		return voList;
	}

	public ActorVO retrieveActorById(Integer actorId) throws SakilaException {
		ActorVO viewObject = null;
		if (actorId != null && actorId > 0) {
			viewObject = actorRepo.findById(actorId).map(actor -> {
				return new ActorVO(actor);
			}).orElseThrow(() -> new SakilaException(SakilaException.ERROR_CODE.REPOSITORY_ERROR, SakilaException.NO_DATA_FOUND_MSG));
		}
		return viewObject;
	}

	public List<ActorVO> retrieveActorByName(String name) throws SakilaException {
		List<ActorVO> voList = null;
		if (!StringUtils.isEmpty(name)) {
			voList = actorRepo.findByName(name).stream().map(actor -> new ActorVO(actor)).collect(Collectors.toList());
		}
		return voList;
	}

	@Transactional
	public ActorVO addActor(ActorVO data) {
		Actor entity = data.toEntity();
		entity.setActorId(null);
		entity = actorRepo.save(entity);
		return entity.toViewObject();
	}

	@Transactional
	public void removeActor(Integer actorId) throws SakilaException {
		try {
			actorRepo.deleteById(actorId);
		} catch (DataAccessException dae) {
			dae.getMessage();
			dae.printStackTrace();
			throw new SakilaException(SakilaException.ERROR_CODE.REPOSITORY_ERROR, dae);
		}
	}
}
