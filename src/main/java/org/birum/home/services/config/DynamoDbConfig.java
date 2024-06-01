package org.birum.home.services.config;
 
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
 
 
@Configuration
@Profile("!local")
public class DynamoDbConfig {
 
  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDbEndpoint;
 
  @Value("${amazon.dynamodb.region}")
  private String amazonDynamoDbRegion;
 
  @Bean
  DynamoDbClient dynamoDbClient() {
	  URI endpointURI = null;
	  try {
		  endpointURI = new URI(amazonDynamoDbEndpoint);
	  }
	  catch (URISyntaxException use) {
		  use.printStackTrace();
	  }
    return DynamoDbClient.builder()
            .region(Region.US_EAST_2)
            .endpointOverride(endpointURI)
            .build();
  }
 
}