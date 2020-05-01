package com.alacriti.poc.multipledbperf.crypto;

import java.io.File;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import com.alacriti.yaf.core.resources.Resources;
//import com.alacriti.yaf.crypto.ncipher.client.NcipherClientConnectorConfigList;
//import com.alacriti.yaf.crypto.ncipher.client.NcipherClientConnectorCreatorFactory;
//import com.alacriti.yaf.hsa.crypto.ncipher.client.EncryptionHelper;
//import com.alacriti.yaf.vo.AppConfig;
//import com.alacriti.yaf.vo.CompntInfo;

public class EncryptionUtil 
{
	
	
	public void init() 
	{
	}
//	protected Logger log = LoggerFactory.getLogger(EncryptionUtil.class);
//
//	protected	EncryptionHelper encryptionhelper = null;
//	protected	Resources resources = null;
//
//
//	public void init() 
//	{
//
//		File xmlFile = new File("./conf/encryption-client-config.xml");
//		JAXBContext jaxbContext;
//		try
//		{
//			jaxbContext = JAXBContext.newInstance(NcipherClientConnectorConfigList.class);
//			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//
//			JAXBElement<NcipherClientConnectorConfigList> jaxbElement = (JAXBElement<NcipherClientConnectorConfigList>) jaxbUnmarshaller
//					.unmarshal(new StreamSource(xmlFile), NcipherClientConnectorConfigList.class);
//
//			NcipherClientConnectorConfigList ncipherClientConnectorConfigList = jaxbElement.getValue();
//
//			ncipherClientConnectorConfigList.setCompntInfo(getComponentInfo());
//			log.info("ncipherClientConnectorConfigList size:" + ncipherClientConnectorConfigList.getConfigList().size());
//
//			NcipherClientConnectorCreatorFactory ncipherClientConnectorCreatorFactory = new NcipherClientConnectorCreatorFactory();
//
//			log.info("ncipherClientConnectorCreatorFactory :" + ncipherClientConnectorCreatorFactory.toString());
//
//			initResources();
//			ncipherClientConnectorCreatorFactory.init(ncipherClientConnectorConfigList);
//
//
//			encryptionhelper = new EncryptionHelper();
//			encryptionhelper.init(ncipherClientConnectorCreatorFactory);
//		}
//		catch (JAXBException e)
//		{
//			log.error("JAXBException occured at init()" + e.getMessage(),e);
//			e.printStackTrace();
//		}
//
//	}
//
//	private void initResources()
//	{
//		resources = new Resources();
//		//resources.init(info);
//	}
//
//	public int desEncrypt(Collection col)
//	{
//		return encryptionhelper.desEncrypt(col);
//	}
//
//	public int desDecrypt(Collection col)
//	{
//		return encryptionhelper.desDecrypt(col);
//	}
//	public String base64Encode(byte [] bytes)
//	{
//		return encryptionhelper.base64Encode(bytes);
//	}
//	public byte[] base64Decode(String stringToDecode)
//	{
//		return encryptionhelper.base64Decode(stringToDecode);
//	}
//
//	private CompntInfo getComponentInfo()
//	{
//		CompntInfo compntInfo = new CompntInfo();
//		compntInfo.setCompntId("1");
//		AppConfig appConfig = new AppConfig();
//		appConfig.setAppId("14");
//		appConfig.setAppInstanceId("01");
//		compntInfo.setAppConfig(appConfig);
//
//		return compntInfo;
//	}

}
