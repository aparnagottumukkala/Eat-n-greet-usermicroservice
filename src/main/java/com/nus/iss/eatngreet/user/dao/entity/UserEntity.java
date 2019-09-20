package com.nus.iss.eatngreet.user.dao.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "phone_no")
	private String phoneNo;

	@Column(name = "gender")
	private Character gender;

	@Column(name = "dob")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dob;

	@Column(name = "password")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Set<AddressEntity> addresses;

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

	public UserEntity(UserEntity user) {
		this.userId = user.getUserId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.emailId = user.getEmailId();
		this.phoneNo = user.getPhoneNo();
		this.gender = user.getGender();
		this.dob = user.getDob();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.isActive = user.getIsActive();
		this.isDeleted = user.getIsDeleted();
		this.createdOn = user.getCreatedOn();
		this.updatedOn = user.getUpdatedOn();
	}

}
