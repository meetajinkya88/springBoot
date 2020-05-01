package com.alacriti.poc.multipledbperf.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.compress.ICompressor;
import com.alacriti.poc.multipledbperf.constants.Constants;
import com.amazonaws.annotation.Beta;

@Component
public class PersistenceTypeFactory  implements ApplicationContextAware
{
	protected Logger log = LoggerFactory.getLogger(PersistenceTypeFactory.class);

	private ApplicationContext appContext;

	private PersistenceTypeFactory()
	{
	}


	public IPersistenceTypeHelper getPersistenceTypeHelper(String persistenceType,String compressorType)
	{
		if(persistenceType == null || 
				persistenceType.isEmpty())
			return null;

		IPersistenceTypeHelper helper = null;
		switch(persistenceType)
		{

		case Constants.PERSISTENCE_TYPE_MYSQL:
			helper =  (MySQLPersistenceTypeHelper) appContext.getBean("MySQLPersistenceTypeHelper");
			((MySQLPersistenceTypeHelper)helper).setCompressor(compressorType);
			return helper;
		case Constants.PERSISTENCE_TYPE_MYSQL_S3: 
			helper =  (MySQLS3PersistenceTypeHelper) appContext.getBean("MySQLS3PersistenceTypeHelper");
			((MySQLS3PersistenceTypeHelper)helper).setCompressor(compressorType);
			return helper;
		case Constants.PERSISTENCE_TYPE_POSGRES:
			helper =  (PosgresPersistenceTypeHelper) appContext.getBean("PosgresPersistenceTypeHelper");
			((PosgresPersistenceTypeHelper)helper).setCompressor(compressorType);
			return helper;

		case Constants.PERSISTENCE_TYPE_POSGRES_S3:
			helper =  (PosgresS3PersistenceTypeHelper) appContext.getBean("PosgresS3PersistenceTypeHelper");
			((PosgresS3PersistenceTypeHelper)helper).setCompressor(compressorType);
			return helper;
			
		case Constants.PERSISTENCE_TYPE_MONGO:
			helper =  (MongoDBPersistenceHelper) appContext.getBean("MongoDBPersistenceHelper");
			((MongoDBPersistenceHelper)helper).setCompressor(compressorType);
			return helper;	
		}

		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContext = applicationContext;
	}
}
