package com.nus.iss.eatngreet.user.util;

import com.nus.iss.eatngreet.user.requestdto.UserSignupRequestDTO;

public class Formatter {
	
	public static UserSignupRequestDTO format(UserSignupRequestDTO user) {
		String str = user.getFirstName();
		user.setFirstName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getEmailId();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getPhoneNo();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		str = user.getLastName();
		user.setLastName(Util.isStringEmpty(str) ? "" : str.trim());
		
		return user;
		
	}

}
