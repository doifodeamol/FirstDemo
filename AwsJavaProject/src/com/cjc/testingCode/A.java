package com.cjc.testingCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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


public class A {

		static AmazonEC2 ec2=null;
		final AmazonEC2 ec22 = null;
		
		public void m1() throws IOException {

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
			
	}
	public void createVolume(){
		
		CreateVolumeRequest ec2Request = new CreateVolumeRequest();
        ec2Request.withSize(2);
        ec2Request.withAvailabilityZone("ec2.ap-south-1");
        CreateVolumeResult ec2Response = ec2.createVolume(ec2Request);
       String volumeId = ec2Response.getVolume().getVolumeId();
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
	public static void main(String[] args) {
		
		
		A a=new A();
		try {
			a.m1();
			a.createVolume();
			a.describeInstance();
			a.describeVolumes();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
