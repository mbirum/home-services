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
	private String url;

	@DynamoDBHashKey
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean isEmpty() {
		return !StringUtils.hasLength(resourceName) || !StringUtils.hasLength(url);
	}

}