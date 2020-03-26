package com.sakila.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String code;

	private Integer capital;

	private String code2;

	private String continent;

	private String country;

	@Column(name="country_id")
	private Integer countryId;

	private float gnp;

	private float GNPOld;

	private String governmentForm;

	private String headOfState;

	private short indepYear;

	@Column(name="last_update")
	private ZonedDateTime lastUpdate;

	private float lifeExpectancy;

	private String localName;

	private String name;

	private Integer population;

	private String region;

	private float surfaceArea;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country1")
	private List<City> cities1;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country2")
	private List<City> cities2;

	public Country() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCapital() {
		return this.capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public String getCode2() {
		return this.code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public float getGnp() {
		return this.gnp;
	}

	public void setGnp(float gnp) {
		this.gnp = gnp;
	}

	public float getGNPOld() {
		return this.GNPOld;
	}

	public void setGNPOld(float GNPOld) {
		this.GNPOld = GNPOld;
	}

	public String getGovernmentForm() {
		return this.governmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}

	public String getHeadOfState() {
		return this.headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	public short getIndepYear() {
		return this.indepYear;
	}

	public void setIndepYear(short indepYear) {
		this.indepYear = indepYear;
	}

	public ZonedDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(ZonedDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public float getLifeExpectancy() {
		return this.lifeExpectancy;
	}

	public void setLifeExpectancy(float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public String getLocalName() {
		return this.localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopulation() {
		return this.population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public float getSurfaceArea() {
		return this.surfaceArea;
	}

	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public List<City> getCities1() {
		return this.cities1;
	}

	public void setCities1(List<City> cities1) {
		this.cities1 = cities1;
	}

	public City addCities1(City cities1) {
		getCities1().add(cities1);
		cities1.setCountry1(this);

		return cities1;
	}

	public City removeCities1(City cities1) {
		getCities1().remove(cities1);
		cities1.setCountry1(null);

		return cities1;
	}

	public List<City> getCities2() {
		return this.cities2;
	}

	public void setCities2(List<City> cities2) {
		this.cities2 = cities2;
	}

	public City addCities2(City cities2) {
		getCities2().add(cities2);
		cities2.setCountry2(this);

		return cities2;
	}

	public City removeCities2(City cities2) {
		getCities2().remove(cities2);
		cities2.setCountry2(null);

		return cities2;
	}

}