package com.helloworld.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.helloworld.entity.ActorEntity;
import com.helloworld.repository.ActorRepository;
import com.helloworld.vo.ActorVO;

@Service
public class ActorService {
	@Autowired
	private ActorRepository actorRepo;

	public List<ActorVO> retrieveAllActors() {
		List<ActorEntity> entityList = actorRepo.findAll();
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
}
