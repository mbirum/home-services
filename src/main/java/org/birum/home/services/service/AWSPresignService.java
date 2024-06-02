package org.birum.home.services.service;

import java.time.Duration;

import org.birum.home.services.entity.NamedResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

@Service
public class AWSPresignService {
	
	@Autowired
	S3Presigner s3Presigner;
	
	@Value("${amazon.presign.durationinminutes}")
	private int amazonPresignDuration;
	
	public String createPresignedGetUrl(NamedResource resource) {
		return createPresignedGetUrl(resource.getBucketName(), resource.getKeyName());
	}
	
    public String createPresignedGetUrl(String bucketName, String keyName) {    	
        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(amazonPresignDuration))
                .getObjectRequest(objectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
        return presignedRequest.url().toExternalForm();
        
    }
}
