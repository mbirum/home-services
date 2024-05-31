package org.birum.home.services.controller;

import org.birum.home.services.entity.NamedResource;
import org.birum.home.services.entity.response.InvalidRequestResponse;
import org.birum.home.services.entity.response.StringResponse;
import org.birum.home.services.exception.ValidationException;
import org.birum.home.services.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
public class ImageController {

	@Autowired
	ResourceService resourceService;

	@GetMapping("/{name}")
	public ResponseEntity<Object> getImageURL(@PathVariable final String name) {
		String imageURL = "";
		try {
			NamedResource imageResource = resourceService.getNamedResource(name);
			if (imageResource.isEmpty()) {
				return new ResponseEntity<>(new StringResponse(imageURL), HttpStatus.NOT_FOUND);
			}
			imageURL = imageResource.getUrl();
		}
		catch (ValidationException ve) {
			return new ResponseEntity<>(new InvalidRequestResponse(ve.getMessage()), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new StringResponse(imageURL), HttpStatus.OK);
	}
}
