package com.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helloworld.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
	@Query ("select a from Actor a where a.firstName like %?1% or a.lastName like %?1%")
	List<Actor> findByName(String name);
}
