package com.nus.iss.eatngreet.user.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupAddressRequestDTO {

	private String blockNo;
	private String floorNo;
	private String unitNo;
	private String buildingName;
	private String streetName;
	private String pincode;
	private String latitude;
	private String longitude;
}
