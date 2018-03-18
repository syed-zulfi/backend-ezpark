package com.apptech.apps.easypark.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PARKING_LOCATION",
	   uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
			   				 @UniqueConstraint(columnNames = "LOCTN_ID")})
public class ParkLocation extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4224325817531465814L;
	@Column(name = "LOCTN_ID")
	private String locationId;
	@Column(name = "LOCTN_NAME")
	private String name;
	@Column(name = "LOCTN_LAT")
	private Double latitude;
	@Column(name = "LOCTN_LONG")
	private Double longitue;
	@Column(name = "LOCTN_CAPTW")
	private Integer capacityTW;
	@Column(name = "LOCTN_CAPFW")
	private Integer capacityFW;
	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private User lctnOwner;
	
	@OneToMany(mappedBy = "spaceLCTN")
	private Set<ParkSpace> spaces =  new HashSet<ParkSpace>();
	
	
	@Override
	public String toString() {
		return "ParkLocation [locationId=" + locationId + ", name=" + name + ", latitude=" + latitude + ", longitue="
				+ longitue + ", capacityTW=" + capacityTW + ", capacityFW=" + capacityFW + ", lctnOwner=" + lctnOwner
				+ ", spaces=" + spaces + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((capacityFW == null) ? 0 : capacityFW.hashCode());
		result = prime * result + ((capacityTW == null) ? 0 : capacityTW.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((lctnOwner == null) ? 0 : lctnOwner.hashCode());
		result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result + ((longitue == null) ? 0 : longitue.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((spaces == null) ? 0 : spaces.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkLocation other = (ParkLocation) obj;
		if (capacityFW == null) {
			if (other.capacityFW != null)
				return false;
		} else if (!capacityFW.equals(other.capacityFW))
			return false;
		if (capacityTW == null) {
			if (other.capacityTW != null)
				return false;
		} else if (!capacityTW.equals(other.capacityTW))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (lctnOwner == null) {
			if (other.lctnOwner != null)
				return false;
		} else if (!lctnOwner.equals(other.lctnOwner))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (longitue == null) {
			if (other.longitue != null)
				return false;
		} else if (!longitue.equals(other.longitue))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (spaces == null) {
			if (other.spaces != null)
				return false;
		} else if (!spaces.equals(other.spaces))
			return false;
		return true;
	}


	public static Set<ParkSpace> allocateSpace(){
	 return null;
	}

}
