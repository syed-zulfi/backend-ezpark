package com.apptech.apps.easypark.dao.entity;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PARKING_TRANSACTIONS",
	   uniqueConstraints = { @UniqueConstraint(columnNames = "ID")})
public class ParkTxn extends BaseEntity implements Serializable{
	private String rtoNumber;
	private String custMobile;
	private Double totalAmount;
	private Date inTime;
	private Date outTime;
	@ManyToOne
	@JoinColumn(name = "SPACE_ID")
	private ParkSpace space;


}
