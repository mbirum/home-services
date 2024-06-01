package org.birum.home.services.entity;

import org.springframework.util.StringUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "BirumServiceResources")
public class NamedResource implements IResource {

	@DynamoDBTyped(DynamoDBAttributeType.S)
	private String resourceName;

	@DynamoDBTyped(DynamoDBAttributeType.S)
	private String bucketName;
	
	@DynamoDBTyped(DynamoDBAttributeType.S)
	private String keyName;

	@DynamoDBHashKey
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