package com.alacriti.poc.multipledbperf.compress.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.compress.DefaultCompressor;
import com.alacriti.poc.multipledbperf.compress.Deflator;
import com.alacriti.poc.multipledbperf.compress.ICompressor;
import com.alacriti.poc.multipledbperf.compress.LZ4Compressor;
import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.persistence.IPersistenceTypeHelper;
import com.alacriti.poc.multipledbperf.persistence.MySQLPersistenceTypeHelper;
import com.alacriti.poc.multipledbperf.persistence.PosgresS3PersistenceTypeHelper;
import com.alacriti.poc.multipledbperf.util.StringUtils;

@Component
public class CompressorFactory  implements ApplicationContextAware
{
	protected Logger log = LoggerFactory.getLogger(CompressorFactory.class);

	private ApplicationContext appContext;

	private CompressorFactory()
	{
	}


	public ICompressor getCompressor(String compressionType)
	{
		log.info("Param CompressionType:" + compressionType);
		if(StringUtils.isEmpty(compressionType))
			//return new DefaultCompressor();
		  return appContext.getBean("DefaultCompressor",DefaultCompressor.class);

		switch(compressionType)
		{
		case Constants.PARAM_COMPRESSION_TYPE_DEFLATOR:
			//return new Deflator();
			 	return  appContext.getBean("Deflator",Deflator.class);

		case Constants.PARAM_COMPRESSION_TYPE_LZ4:
			//return new LZ4Compressor();
			  return  appContext.getBean("LZ4Compressor",LZ4Compressor.class);

		default:
			//return new DefaultCompressor();
		    return appContext.getBean("DefaultCompressor",DefaultCompressor.class);

		}
	}
	
	public ICompressor getDefaultCompressor()
	{
		return new DefaultCompressor();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.appContext = applicationContext;
	}
}
