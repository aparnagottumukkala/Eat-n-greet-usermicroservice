package com.nus.iss.eatngreet.user.util;

import com.nus.iss.eatngreet.user.responsedto.CommonResponseDTO;

public class ResponseUtil {

	public static void prepareResponse(CommonResponseDTO response, String message, String status, String info,
			boolean success) {
		if (response != null) {
			response.setMessage(message);
			response.setStatus(status);
			response.setSuccess(success);
			response.setInfo(info);
		}
	}
}
