package org.birum.home.services.service;

import org.birum.home.services.dao.ResourceDAO;
import org.birum.home.services.entity.NamedResource;
import org.birum.home.services.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.newrelic.api.agent.Trace;

@Service
public class ResourceService {

	@Autowired
	ResourceDAO resourceDAO;
	
	@Trace
	public NamedResource getNamedResource(final String name) throws ValidationException {
		validate(name);
		return resourceDAO.getResourceByName(name);
	}
	
	private void validate(final String name) {
		if (!StringUtils.hasLength(name)) {
			throw new ValidationException("Resource name cannot be empty");
		}
	}
}
