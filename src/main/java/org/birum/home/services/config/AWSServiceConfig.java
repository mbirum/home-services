package org.birum.home.services.config;
 
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
 
 
@Configuration
@Profile("!local")
public class AWSServiceConfig {
 
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
    		.credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
            .region(Region.of(amazonDynamoDbRegion))
            .endpointOverride(endpointURI)
            .build();
  }
  
  @Bean
  S3Presigner s3Presigner() {
	  return S3Presigner.builder()
      		.region(Region.of(amazonDynamoDbRegion))
      		.credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
      		.build();
  }
 
}