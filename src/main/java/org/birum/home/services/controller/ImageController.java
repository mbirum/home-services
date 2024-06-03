package org.birum.home.services.controller;

import org.birum.home.services.entity.NamedResource;
import org.birum.home.services.entity.response.ErrorResponse;
import org.birum.home.services.entity.response.StringResponse;
import org.birum.home.services.exception.ValidationException;
import org.birum.home.services.service.AWSPresignService;
import org.birum.home.services.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;

@RestController
@RequestMapping("images")
public class ImageController {

	@Autowired
	ResourceService resourceService;
	
	@Autowired
	AWSPresignService awsPresignService;

	@CrossOrigin(origins = {"http://mattbirum.com", "https://mattbirum.com"})
	@GetMapping("/{name}")
	public ResponseEntity<Object> getImageURL(@PathVariable final String name) {
		String imageURL = "";
		try {
			NamedResource imageResource = resourceService.getNamedResource(name);
			if (imageResource.isEmpty()) {
				return new ResponseEntity<>(new StringResponse(imageURL), HttpStatus.NOT_FOUND);
			}
			imageURL = awsPresignService.createPresignedGetUrl(imageResource);
		}
		catch (ValidationException ve) {
			return new ResponseEntity<>(new ErrorResponse(ve.getMessage()), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new StringResponse(imageURL), HttpStatus.OK);
	}
	
}
