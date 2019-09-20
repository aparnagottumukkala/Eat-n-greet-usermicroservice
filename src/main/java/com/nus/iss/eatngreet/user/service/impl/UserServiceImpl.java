package com.nus.iss.eatngreet.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nus.iss.eatngreet.user.dao.entity.AddressEntity;
import com.nus.iss.eatngreet.user.dao.entity.RoleEntity;
import com.nus.iss.eatngreet.user.dao.entity.UserEntity;
import com.nus.iss.eatngreet.user.dao.repository.RoleRepository;
import com.nus.iss.eatngreet.user.dao.repository.UserRepository;
import com.nus.iss.eatngreet.user.requestdto.UserSignupRequestDTO;
import com.nus.iss.eatngreet.user.responsedto.CommonResponseDTO;
import com.nus.iss.eatngreet.user.responsedto.DataResponseDTO;
import com.nus.iss.eatngreet.user.service.UserService;
import com.nus.iss.eatngreet.user.util.ResponseUtil;
import com.nus.iss.eatngreet.user.util.Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public CommonResponseDTO userSignup(UserSignupRequestDTO user) {
		CommonResponseDTO response = new CommonResponseDTO();
		if (!Util.isValidEmail(user.getEmailId())) {
			ResponseUtil.prepareResponse(response, "Invalid email id.", "FAILURE", "Incorrect Email-id.", false);
		} else if (!Util.isValidSGPhoneNo(user.getPhoneNo())) {
			ResponseUtil.prepareResponse(response, "Invalid mobile number.", "FAILURE", "Incorrect phone no.", false);
		} else if (userRepository.findByEmailId(user.getEmailId()).isPresent()) {
			ResponseUtil.prepareResponse(response, "Email-id already registered.", "FAILURE",
					"Email-id already registered.", false);
		} else if (userRepository.findByPhoneNo(user.getPhoneNo()).isPresent()) {
			ResponseUtil.prepareResponse(response, "Phone number already registered.", "FAILURE",
					"Mobile Number already registered.", false);
		} else if (!user.getPassword().equals(user.getConfirmPassword())) {
			ResponseUtil.prepareResponse(response, "Password and confirm password must be the same.", "FAILURE",
					"Password and confirm password are different.", false);
		} else {
			AddressEntity newUserAddress = new AddressEntity();
			BeanUtils.copyProperties(user.getAddress(), newUserAddress);
			if (Util.isStringEmpty(newUserAddress.getUnitNo()) || Util.isStringEmpty(newUserAddress.getFloorNo())
					|| Util.isStringEmpty(newUserAddress.getBuildingName())
					|| Util.isStringEmpty(newUserAddress.getPincode())
					|| Util.isStringEmpty(newUserAddress.getBlockNo())) {
				ResponseUtil.prepareResponse(response,
						"Level no., unit no., block no., building name and pincode are mandatory fields.", "FAILURE",
						"Mandatory fields missing..", false);
			} else {
				newUserAddress.setIsActive(true);
				newUserAddress.setIsDeleted(false);
				Set<AddressEntity> addresses = new HashSet<AddressEntity>();
				addresses.add(newUserAddress);
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				UserEntity newUser = new UserEntity();
				BeanUtils.copyProperties(user, newUser);
				newUser.setPassword(encoder.encode(user.getPassword()));
				newUser.setAddresses(addresses);
				Set<RoleEntity> roles = new HashSet<RoleEntity>();
				RoleEntity userRole = roleRepository.findByRole("USER").get();
				roles.add(userRole);
				newUser.setRoles(roles);
				newUser.setIsActive(true);
				newUser.setIsDeleted(false);
				userRepository.save(newUser);
				ResponseUtil.prepareResponse(response, "Successfully registered.", "SUCCESS",
						"Successfully registered.", true);
			}
		}
		return response;
	}

//	@Override
//	public CommonResponseDTO userSignin(UserSigninRequestDTO userReq) {
//		CommonResponseDTO response = new CommonResponseDTO();
//		if (!Util.isValidEmail(userReq.getEmailId())) {
//			ResponseUtil.prepareResponse(response, "Invalid email id.", "FAILURE", "Incorrect Email-id.", false);
//		} else {
//			Optional<UserEntity> user = userRepository.findByEmail(userReq.getEmailId());
//			if (user.isEmpty()) {
//				ResponseUtil.prepareResponse(response, "No record exists with the given email id.", "FAILURE",
//						"Unregistered Email-id.", false);
//			} else {
////				encryptedPassword = 
//			}
//		}
//		return response;
//	}

//	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
//		return "redirect:/login";
//	}

	@Override
	public DataResponseDTO getUserAddressAndNameFromEmailIds(HashMap<String, Set<String>> emailIdObj) {
		DataResponseDTO response = new DataResponseDTO();
		HashMap<Object, Object> data = new HashMap<>();
		Set<String> emailIds = emailIdObj.get("emailIds");
		List<String> emailIdList = new ArrayList<String>();
		emailIdList.addAll(emailIds);
		List<UserEntity> users = userRepository.findByEmailIds(emailIdList);
		ResponseUtil.prepareResponse(response, "Successfully fetched User details from email id.", "SUCCESS",
				"Successfully fetched User details from email id.", true);
		Map<String, Object> infoMap = new HashMap<String, Object>();
		for (UserEntity user : users) {
			Map<String, Object> userInfoMap = new HashMap<String, Object>();
			userInfoMap.put("firstName", user.getFirstName());
			userInfoMap.put("lastName", user.getLastName());
			Set<AddressEntity> addresses = user.getAddresses();
			List<Map> formattedAddresses = new ArrayList<>(); 
			for (AddressEntity address: addresses) {
				Map<String, String> addressInfo = new HashMap<String, String>();
				addressInfo.put("blockNo", address.getBlockNo());
				addressInfo.put("floorNo", address.getFloorNo());
				addressInfo.put("unitNo", address.getUnitNo());
				addressInfo.put("buildingName", address.getBuildingName());
				addressInfo.put("streetName", address.getStreetName());
				addressInfo.put("pincode", address.getPincode());
				addressInfo.put("latitude", address.getLatitude());
				addressInfo.put("longitude", address.getLongitude());
				formattedAddresses.add(addressInfo);
			}
			userInfoMap.put("addresses", formattedAddresses);
			infoMap.put(user.getEmailId(), userInfoMap);
		}
		data.put("userInfo", infoMap);
		response.setData(data);
		return response;
	}

}
