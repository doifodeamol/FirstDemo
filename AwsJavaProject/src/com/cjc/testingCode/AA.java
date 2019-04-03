package com.cjc.testingCode;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Region;
import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;

public class AA {

	public static void main(String[] args) {
	
		final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().build();

		        DescribeRegionsResult regions_response = ec2.describeRegions();

		        for(Region region : regions_response.getRegions()) {
		            System.out.printf(
		                "Found region %s " +
		                "with endpoint %s",
		                region.getRegionName(),
		                region.getEndpoint());
		        }

		        DescribeAvailabilityZonesResult zones_response =
		            ec2.describeAvailabilityZones();

		        for(AvailabilityZone zone : zones_response.getAvailabilityZones()) {
		            System.out.printf(
		                "Found availability zone %s " +
		                "with status %s " +
		                "in region %s",
		                zone.getZoneName(),
		                zone.getState(),
		                zone.getRegionName());
		        }
		    }
		
	}

