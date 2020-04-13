package com.sakila.view;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import com.sakila.model.Actor;

public class ActorVO implements Serializable {
//	public ActorVO(){
//		
//	}
	
	public ActorVO(Actor entity){
		BeanUtils.copyProperties(entity, this);
	}
	
	public Actor toEntity(){
		Actor entity = new Actor();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
	
	private static final long serialVersionUID = 2712387406223494017L;

	private Integer actorId;
	@NotBlank(message = "First Name is required")
	private String firstName;
	@NotBlank(message = "Last Name is required")
	private String lastName;

	private ZonedDateTime lastUpdate;

	public Integer getActorId() {
		return actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ZonedDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(ZonedDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
