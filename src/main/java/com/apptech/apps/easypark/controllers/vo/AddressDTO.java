package com.apptech.apps.easypark.controllers.vo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "building", "street", "city", "state", "country", "pin" })
public class AddressDTO {

	@JsonProperty("building")
	private String building;
	@JsonProperty("street")
	private String street;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("country")
	private String country;
	@JsonProperty("pin")
	private Integer pin;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("building")
	public String getBuilding() {
		return building;
	}

	@JsonProperty("building")
	public void setBuilding(String building) {
		this.building = building;
	}

	@JsonProperty("street")
	public String getStreet() {
		return street;
	}

	@JsonProperty("street")
	public void setStreet(String street) {
		this.street = street;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("pin")
	public Integer getPin() {
		return pin;
	}

	@JsonProperty("pin")
	public void setPin(Integer pin) {
		this.pin = pin;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
