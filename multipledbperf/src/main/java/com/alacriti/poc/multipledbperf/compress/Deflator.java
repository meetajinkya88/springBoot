package com.alacriti.poc.multipledbperf.compress;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.util.ExceptionUtil;


@Component("Deflator")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class Deflator extends DefaultCompressor 
{
	protected Logger log = LoggerFactory.getLogger(Deflator.class);

	public static final String encodingType = "UTF-8";
	public Deflator() 
	{

	}

	@Override
	public String compress(String unCompressedJson)
	{
		Deflater deflater = new Deflater();
		byte[] jsonBytes;
		byte[] output ;
		try 
		{
			jsonBytes = unCompressedJson.getBytes(encodingType);
			deflater.setInput(jsonBytes);
			deflater.setLevel(Deflater.DEFAULT_COMPRESSION);
			deflater.finish();
			ByteArrayOutputStream bos = new ByteArrayOutputStream(jsonBytes.length);
			byte[] buffer = new byte[1024];
			while(!deflater.finished()) 
			{
				int count = deflater.deflate(buffer);
				bos.write(buffer, 0, count);
			}
			bos.close();
			output = bos.toByteArray();

			return encodeBase64(output);
		} 
		catch (Exception e) 
		{
			log.error("Exception occured at compress" , e);
			ExceptionUtil.getStackTrace(e);
		}

		return null;
	}

	@Override
	public String deCompress(String compressedJson) 
	{
		byte[] uncompressedBytes = null;
		
		try 
		{
			
			uncompressedBytes = decodeBase64(compressedJson);
			Inflater inflater = new Inflater();
			inflater.setInput(uncompressedBytes);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(uncompressedBytes.length);
			byte[] buffer = new byte[1024];
			while (!inflater.finished()) 
			{
				int count = inflater.inflate(buffer);
				bos.write(buffer, 0, count);
			}
			bos.close();
			byte[] output = bos.toByteArray();
			return new String(output);
		} 
		catch (Exception e) 
		{
			log.error("Exception occured at deCompress" , e);
			ExceptionUtil.getStackTrace(e);
		}

		return null;
	}

	public static String encodeBase64(byte[] bytes) throws Exception 
	{
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static byte[] decodeBase64(String str) throws Exception 
	{
		return Base64.getDecoder().decode(str);
	}

}
