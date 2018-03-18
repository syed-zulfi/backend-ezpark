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
	
	
	
	public String getSpaceID() {
		return spaceID;
	}

	public void setSpaceID(String spaceID) {
		this.spaceID = spaceID;
	}

	public Vehicle getSpaceFOR() {
		return spaceFOR;
	}

	public void setSpaceFOR(Vehicle spaceFOR) {
		this.spaceFOR = spaceFOR;
	}

	public Status getSpaceSTATUS() {
		return spaceSTATUS;
	}

	public void setSpaceSTATUS(Status spaceSTATUS) {
		this.spaceSTATUS = spaceSTATUS;
	}

	public Double getSpaceChargePerHour() {
		return spaceChargePerHour;
	}

	public void setSpaceChargePerHour(Double spaceChargePerHour) {
		this.spaceChargePerHour = spaceChargePerHour;
	}

	public ParkLocation getSpaceLCTN() {
		return spaceLCTN;
	}

	public void setSpaceLCTN(ParkLocation spaceLCTN) {
		this.spaceLCTN = spaceLCTN;
	}

	public Set<ParkTxn> getSpaceTXN() {
		return spaceTXN;
	}

	public void setSpaceTXN(Set<ParkTxn> spaceTXN) {
		this.spaceTXN = spaceTXN;
	}

	
	
	
	@Override
	public String toString() {
		return "ParkSpace [spaceID=" + spaceID + ", spaceFOR=" + spaceFOR + ", spaceSTATUS=" + spaceSTATUS
				+ ", spaceChargePerHour=" + spaceChargePerHour + ", spaceLCTN=" + spaceLCTN + ", spaceTXN=" + spaceTXN
				+ "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((spaceChargePerHour == null) ? 0 : spaceChargePerHour.hashCode());
		result = prime * result + ((spaceFOR == null) ? 0 : spaceFOR.hashCode());
		result = prime * result + ((spaceID == null) ? 0 : spaceID.hashCode());
		result = prime * result + ((spaceLCTN == null) ? 0 : spaceLCTN.hashCode());
		result = prime * result + ((spaceSTATUS == null) ? 0 : spaceSTATUS.hashCode());
		result = prime * result + ((spaceTXN == null) ? 0 : spaceTXN.hashCode());
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
		ParkSpace other = (ParkSpace) obj;
		if (spaceChargePerHour == null) {
			if (other.spaceChargePerHour != null)
				return false;
		} else if (!spaceChargePerHour.equals(other.spaceChargePerHour))
			return false;
		if (spaceFOR != other.spaceFOR)
			return false;
		if (spaceID == null) {
			if (other.spaceID != null)
				return false;
		} else if (!spaceID.equals(other.spaceID))
			return false;
		if (spaceLCTN == null) {
			if (other.spaceLCTN != null)
				return false;
		} else if (!spaceLCTN.equals(other.spaceLCTN))
			return false;
		if (spaceSTATUS != other.spaceSTATUS)
			return false;
		if (spaceTXN == null) {
			if (other.spaceTXN != null)
				return false;
		} else if (!spaceTXN.equals(other.spaceTXN))
			return false;
		return true;
	}




	public enum Status {
		AVAILABLE, 
		ENGANGED;
	}
	
	public enum Vehicle {
		BIKE, 
		CAR;
	}
}
