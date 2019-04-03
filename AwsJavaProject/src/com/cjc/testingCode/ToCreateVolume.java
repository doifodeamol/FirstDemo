package com.cjc.testingCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AttachVolumeRequest;
import com.amazonaws.services.ec2.model.AttachVolumeResult;
import com.amazonaws.services.ec2.model.CreateVolumeRequest;
import com.amazonaws.services.ec2.model.CreateVolumeResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public class ToCreateVolume {

	public static void main(String[] args) {
		
        try {
			InputStream credentialsAsStream = new FileInputStream("AwsCredentials.properties");
			
			AWSCredentials credentials = new PropertiesCredentials(credentialsAsStream);
			
			AmazonEC2 ec2 = new AmazonEC2Client(credentials);
			
			ec2.setEndpoint("https://ec2.ap-south-1.amazonaws.com");

			RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
					.withInstanceType("t2.micro")
					.withImageId("ami-0711d827876cdd81a")
					.withMinCount(1)
					.withMaxCount(1)
					.withSecurityGroupIds("sg-022aa644412ac49ab")
					.withKeyName("doifodeamolkey");
			
			RunInstancesResult runInstances = ec2.runInstances(runInstancesRequest);
			System.out.println(runInstances);
			
			 CreateVolumeRequest request = new CreateVolumeRequest()
		        		.withAvailabilityZone("ap-south-1a")
		        		.withSize(2)
		        		.withVolumeType("gp2");
		        CreateVolumeResult response = ec2.createVolume(request);
		        System.out.println(response);
		        
		       /* AttachVolumeRequest request1 = new AttachVolumeRequest()
		        		.withDevice("/dev/sda1")
		        		.withInstanceId("i-01474ef662b89480")
		                .withVolumeId("vol-1234567890abcdef0");
		        AttachVolumeResult response1 = ec2.attachVolume(request1);*/
			
			
		} catch (IllegalArgumentException | AmazonClientException | IOException e) {
			e.printStackTrace();
		}
	}

}
