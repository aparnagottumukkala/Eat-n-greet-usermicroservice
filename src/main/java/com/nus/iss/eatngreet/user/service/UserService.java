package com.nus.iss.eatngreet.user.service;

import java.util.HashMap;
import java.util.Set;

import com.nus.iss.eatngreet.user.requestdto.UserSignupRequestDTO;
import com.nus.iss.eatngreet.user.responsedto.CommonResponseDTO;
import com.nus.iss.eatngreet.user.responsedto.DataResponseDTO;

public interface UserService {

	public CommonResponseDTO userSignup(UserSignupRequestDTO user);
//	public CommonResponseDTO userSignin(UserSigninRequestDTO user);
	public DataResponseDTO getUserAddressAndNameFromEmailIds(HashMap<String, Set<String>> emailIdObj);

}
