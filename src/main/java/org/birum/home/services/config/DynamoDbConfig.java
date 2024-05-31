package org.birum.home.services.config;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
 
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
 
@Configuration
@Profile("!local")
public class DynamoDbConfig {
 
  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDbEndpoint;
 
  @Value("${amazon.dynamodb.region}")
  private String amazonDynamoDbRegion;
 
  @Bean
  AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard()
      .withEndpointConfiguration(new EndpointConfiguration(amazonDynamoDbEndpoint, amazonDynamoDbRegion))
        .build();
  }
 
}