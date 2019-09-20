package com.nus.iss.eatngreet.user.requestdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSigninRequestDTO {
	
	private String emailId;
	private String password;

}
