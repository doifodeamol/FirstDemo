package com.cjc.cloud;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateVolumeRequest;
import com.amazonaws.services.ec2.model.CreateVolumeResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public class CloudeConnection {

	AmazonEC2 ec2 = null;
	public void createInstance()
	{
	try {
		InputStream credentialsAsStream = new FileInputStream("AwsCredentials.properties");
		
		AWSCredentials credentials = new PropertiesCredentials(credentialsAsStream);
		
		ec2 = new AmazonEC2Client(credentials);
		
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
	} catch (IllegalArgumentException | AmazonClientException | IOException e) {
		e.printStackTrace();
	}
	}
	public void createVolume(){
		CreateVolumeRequest request = new CreateVolumeRequest()
        		.withAvailabilityZone("ap-south-1a")
        		.withSize(2)
        		.withVolumeType("gp2");
        CreateVolumeResult response = ec2.createVolume(request);
        String volumeId = response.getVolume().getVolumeId();
        System.out.println(response);
        System.out.println(volumeId);
	}
	public void describeInstance()
    {
			 DescribeInstancesRequest ec2Request = new DescribeInstancesRequest();
             DescribeInstancesResult ec2Response = ec2.describeInstances(ec2Request);
				
			 int nResult = ec2Response.getReservations().size();
			 System.out.println("describeInstance = = "+nResult);
    }	
	public void describeVolumes()
    {
			DescribeVolumesRequest ec2Request = new DescribeVolumesRequest();
            DescribeVolumesResult ec2Response = ec2.describeVolumes(ec2Request);
			int nResult = ec2Response.getVolumes().size();
			System.out.println("describeVolumes ==  "+nResult);
	}
	public static void main(String[] args){
		CloudeConnection con = new CloudeConnection();
		con.createInstance();
		con.describeInstance();
		con.createVolume();
		con.describeInstance();
		
		
	}

}
