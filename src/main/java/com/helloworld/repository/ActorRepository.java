package com.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helloworld.entity.ActorEntity;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
	@Query ("select a from ActorEntity a where a.firstName like %?1% or a.lastName like %?1%")
	List<ActorEntity> findByName(String name);
}
