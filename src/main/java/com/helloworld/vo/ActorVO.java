package com.helloworld.vo;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class ActorVO implements Serializable {

	private static final long serialVersionUID = 2712387406223494017L;

	private Integer actorId;

	private String firstName;

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
