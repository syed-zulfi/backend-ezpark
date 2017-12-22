package com.apptech.apps.easypark.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.apptech.apps.easypark.controllers.vo.AddressDTO;

@Entity
@Table(name = "ADDRESS")
public class Address extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4579524582692702634L;
	@Column(name = "BUILDING")
	private String building;
	@Column(name = "STREET")
	private String street;
	@Column(name = "CITY")
	private String city;
	@Column(name = "STATE")
	private String state;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "PIN")
	private Integer pin;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 * @param aDTO
	 * @return
	 */
	public static Address createAddress(AddressDTO aDTO) {
		Address address = new Address();
		address.setBuilding(aDTO.getBuilding());
		address.setCity(aDTO.getCity());
		address.setCountry(aDTO.getCountry());
		address.setPin(aDTO.getPin());
		address.setStreet(aDTO.getStreet());
		address.setState(aDTO.getState());
		return address;
	}

}
