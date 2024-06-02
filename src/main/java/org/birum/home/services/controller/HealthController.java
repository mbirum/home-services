package org.birum.home.services.controller;

import org.birum.home.services.dao.ResourceDAO;
import org.birum.home.services.entity.response.ErrorResponse;
import org.birum.home.services.entity.response.StringResponse;
import org.birum.home.services.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@Autowired
	ResourceDAO resourceDAO;

	@GetMapping("/healthcheck")
	public ResponseEntity<Object> checkHealth() {
		try {
			Integer resourceTableSize = resourceDAO.getTableSize();
			if (null == resourceTableSize) {
				throw new RuntimeException("Unable to get resource table size. Result is null");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ErrorResponse(e.getMessage(), ExceptionUtils.getStackTraceAsString(e)), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new StringResponse("Success"), HttpStatus.OK);
	}
}
