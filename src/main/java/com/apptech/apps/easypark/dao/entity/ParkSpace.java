package com.apptech.apps.easypark.dao.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PARKING_SPACE", 
       uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
    		   				 @UniqueConstraint(columnNames = "SPACE_ID")
                           })
public class ParkSpace extends BaseEntity implements Serializable{

	private String spaceID;
	private Vehicle spaceFOR;
	private Status spaceSTATUS;
	private Double   spaceCharge;
	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private ParkLocation spaceLCTN;
	@OneToMany(mappedBy = "space")
	private Set<ParkTxn> spaceTXN;
	
	
	
	public enum Status {
		AVAILABLE, 
		ENGANGED;
	}
	
	public enum Vehicle {
		BIKE, 
		CAR;
	}
}
