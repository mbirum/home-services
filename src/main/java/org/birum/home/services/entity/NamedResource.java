package org.birum.home.services.entity;

import org.springframework.util.StringUtils;

public class NamedResource implements IResource {

	private String resourceName;

	private String bucketName;
	
	private String keyName;

	public NamedResource() {
		
	}
	
	public NamedResource(String resourceName, String bucketName, String keyName) {
		this.resourceName = resourceName;
		this.bucketName = bucketName;
		this.keyName = keyName;
	}
	
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getBucketName() {
		return bucketName;
	}
	
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public String getKeyName() {
		return keyName;
	}
	
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	@Override
	public boolean isEmpty() {
		return !StringUtils.hasLength(resourceName) 
				|| !StringUtils.hasLength(bucketName)
				|| !StringUtils.hasLength(keyName);
	}

}