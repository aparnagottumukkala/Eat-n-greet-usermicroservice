package com.nus.iss.eatngreet.user.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class AddressEntity {

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressId;

	@Column(name = "block_no")
	private String blockNo;

	@Column(name = "floor_no")
	private String floorNo;

	@Column(name = "unit_no")
	private String unitNo;

	@Column(name = "building_name")
	private String buildingName;

	@Column(name = "streetName")
	private String streetName;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "is_active", columnDefinition = "TINYINT(1) default 1")
	private Boolean isActive;

	@Column(name = "is_deleted", columnDefinition = "TINYINT(1) default 0")
	private Boolean isDeleted;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", length = 19, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", length = 19, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date updatedOn;

}
