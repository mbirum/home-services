package org.birum.home.services.dao;

import java.util.HashMap;
import java.util.Map;

import org.birum.home.services.entity.EmptyResource;
import org.birum.home.services.entity.NamedResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

@Component
public class ResourceDAO {

	@Autowired
	DynamoDbClient dynamoDbClient;

	@Value("${amazon.dynamodb.tablename}")
	private String tableName;

	public ResourceDAO() {}

	public NamedResource getResourceByName(String name) {
		HashMap<String, AttributeValue> attrValues = new HashMap<>();
		attrValues.put(":resourceName", AttributeValue.builder().s(name).build());

		QueryRequest queryReq = QueryRequest.builder().tableName(tableName)
				.keyConditionExpression("resourceName = :resourceName").expressionAttributeValues(attrValues).build();

		try {
			QueryResponse response = dynamoDbClient.query(queryReq);
			if (response.hasItems()) {
				Map<String, AttributeValue> item = response.items().iterator().next();
				return new NamedResource(item.get("resourceName").s(), item.get("bucketName").s(), item.get("keyName").s());
			}
		}
		catch (DynamoDbException e) {
			// TODO - create new Application exception and create clean message here, also NewRelic.noticeError
			e.printStackTrace();
		}

		return new EmptyResource();
	}

	public Integer getTableSize() {
		try {
			ScanRequest scanRequest = ScanRequest.builder().tableName(tableName).build();
			ScanResponse response = dynamoDbClient.scan(scanRequest);
			return response.items().size();
		}
		catch (DynamoDbException e) {
			e.printStackTrace();
		}
		return null;
	}

}