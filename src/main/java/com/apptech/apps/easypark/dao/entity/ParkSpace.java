package com.apptech.apps.easypark.dao.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 8801350073253036241L;
	@Column(name = "SPACE_ID")
	private String spaceID;
	@Column(name = "SPACE_FOR")
	private Vehicle spaceFOR;
	@Column(name = "SPACE_STATUS")
	private Status spaceSTATUS;
	@Column(name = "SPACE_CHRGE_PER_HOUR")
	private Double   spaceChargePerHour;
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
