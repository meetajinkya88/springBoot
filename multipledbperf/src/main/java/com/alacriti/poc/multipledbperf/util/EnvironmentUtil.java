package com.alacriti.poc.multipledbperf.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.constants.Constants;

@Component
public class EnvironmentUtil 
{
	private static Environment env;

	@Autowired
	protected Environment env1;

	@PostConstruct     
	private void initStaticFields() 
	{
		env = this.env1;
	}
	public static String getS3bucketName() 
	{
		return env.getProperty(Constants.PropertyConstants.PARAM_AWS_S3_BUCKET_NAME, "alac-amf-lazy-load");
	}

	public static String getS3RegionName() 
	{
		return env.getProperty(Constants.PropertyConstants.PARAM_AWS_S3_REGION_NAME, "us-west-1");
	}
}
