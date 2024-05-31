package org.birum.home.services.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.birum.home.services.entity.EmptyResource;
import org.birum.home.services.entity.NamedResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

@Component
public class ResourceDAO {

	private final DynamoDBMapper mapper;

	public ResourceDAO(AmazonDynamoDB dynamoDb) {
		this.mapper = new DynamoDBMapper(dynamoDb);
	}

	public NamedResource getResourceByName(String name) {
		Collection<NamedResource> resources = new ArrayList<NamedResource>();
		DynamoDBQueryExpression<NamedResource> query = new DynamoDBQueryExpression<NamedResource>();
		query.setScanIndexForward(false);

		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		expressionAttributeValues.put(":resourceName", new AttributeValue().withS(name));

		query.withKeyConditionExpression("resourceName = :resourceName")
				.withExpressionAttributeValues(expressionAttributeValues);

		resources.addAll(mapper.query(NamedResource.class, query));

		if (!CollectionUtils.isEmpty(resources)) {
			return resources.iterator().next();
		}
		return new EmptyResource();
	}

}