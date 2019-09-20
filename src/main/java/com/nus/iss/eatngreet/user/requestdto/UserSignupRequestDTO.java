package com.nus.iss.eatngreet.user.requestdto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDTO {
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNo;
	private Character gender;
    @JsonFormat(pattern="dd/MM/yyyy")
	private Date dob;
	private String password;
	private String confirmPassword;
	private UserSignupAddressRequestDTO address;

}
