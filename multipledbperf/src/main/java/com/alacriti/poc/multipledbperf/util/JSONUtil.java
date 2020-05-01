package com.alacriti.poc.multipledbperf.util;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alacriti.poc.multipledbperf.compress.ICompressor;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil 
{
	protected static Logger log = LoggerFactory.getLogger(JSONUtil.class);

	public static void convertObjectToJson(ICompressor compressor, List<CustomerFeedVO> objectList) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		boolean isSuccess = objectList.stream().allMatch(item->{
			try 
			{
				//log.info("Compressor is:" + compressor );
				String compressedJson = compressor.compress(mapper.writeValueAsString(item));
				//log.info("Compressed Json is:"+ compressedJson);
				item.setJsonText(compressedJson);
				//item.setJsonText(mapper.writeValueAsString(item));


			} catch (JsonProcessingException e) 
			{
				log.error("JSONProcessign Exception occured at convertObjectToJson " + e);
				return false;
			}	
			return true;
		});
		
		if(!isSuccess)
			throw new Exception("JSONProcessign Exception occured at convertObjectToJson");
	} 
	
	public static CustomerFeedVO convertJsonToObject( String jsonText) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonText, CustomerFeedVO.class);
	} 
}
