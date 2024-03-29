package com.sakila.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inventory_id")
	private Integer inventoryId;

	@Column(name="last_update")
	private ZonedDateTime lastUpdate;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="inventory")
	private List<Rental> rentals;

	public Inventory() {
	}

	public Integer getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public ZonedDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(ZonedDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setInventory(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setInventory(null);

		return rental;
	}

}