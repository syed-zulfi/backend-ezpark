package com.apptech.apps.easypark.dao.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PARKING_TRANSACTIONS", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class ParkTxn extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7052548938647938716L;
	@Column(name = "REG_NUMBER")
	private String rtoNumber;
	@Column(name = "CUST_MOBILE")
	private String custMobile;
	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount;
	@Column(name = "IN_TIME")
	private Date inTime;
	@Column(name = "OUT_TIME")
	private Date outTime;
	@ManyToOne
	@JoinColumn(name = "SPACE_ID")
	private ParkSpace space;

	public String getRtoNumber() {
		return rtoNumber;
	}

	public void setRtoNumber(String rtoNumber) {
		this.rtoNumber = rtoNumber;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public ParkSpace getSpace() {
		return space;
	}

	public void setSpace(ParkSpace space) {
		this.space = space;
	}

	@Override
	public String toString() {
		return "ParkTxn [rtoNumber=" + rtoNumber + ", custMobile=" + custMobile + ", totalAmount=" + totalAmount
				+ ", inTime=" + inTime + ", outTime=" + outTime + ", space=" + space + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custMobile == null) ? 0 : custMobile.hashCode());
		result = prime * result + ((inTime == null) ? 0 : inTime.hashCode());
		result = prime * result + ((outTime == null) ? 0 : outTime.hashCode());
		result = prime * result + ((rtoNumber == null) ? 0 : rtoNumber.hashCode());
		result = prime * result + ((space == null) ? 0 : space.hashCode());
		result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkTxn other = (ParkTxn) obj;
		if (custMobile == null) {
			if (other.custMobile != null)
				return false;
		} else if (!custMobile.equals(other.custMobile))
			return false;
		if (inTime == null) {
			if (other.inTime != null)
				return false;
		} else if (!inTime.equals(other.inTime))
			return false;
		if (outTime == null) {
			if (other.outTime != null)
				return false;
		} else if (!outTime.equals(other.outTime))
			return false;
		if (rtoNumber == null) {
			if (other.rtoNumber != null)
				return false;
		} else if (!rtoNumber.equals(other.rtoNumber))
			return false;
		if (space == null) {
			if (other.space != null)
				return false;
		} else if (!space.equals(other.space))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		return true;
	}
	
	

}
