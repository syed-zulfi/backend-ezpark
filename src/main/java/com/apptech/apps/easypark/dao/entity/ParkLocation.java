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
	
	
	public static Set<ParkSpace> allocateSpace(){
	 return null;
	}

}
