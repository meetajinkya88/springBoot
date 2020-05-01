package com.alacriti.poc.multipledbperf.compress;

import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.util.ExceptionUtil;

import net.jpountz.lz4.LZ4CompressorWithLength;
import net.jpountz.lz4.LZ4DecompressorWithLength;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

@Component("LZ4Compressor")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class LZ4Compressor extends DefaultCompressor 
{
	protected Logger log = LoggerFactory.getLogger(Deflator.class);

	public static final String encodingType = "UTF-8";
	
	public LZ4Compressor() 
	{

	}

	@Override
	public String compress(String unCompressedJson)
	{
		byte[] jsonBytes;
		byte[] output ;
		try 
		{
			LZ4Factory factory = LZ4Factory.fastestInstance();
			jsonBytes = unCompressedJson.getBytes(encodingType);
			net.jpountz.lz4.LZ4CompressorWithLength compressor = new LZ4CompressorWithLength(factory.fastCompressor());
		
			//	final int decompressedLength = jsonBytes.length;
			//	int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
		//	output= new byte[maxCompressedLength];
		//	int compressedLength = compressor.compress(jsonBytes, 0, decompressedLength, output, 0, maxCompressedLength);
			
			return encodeBase64(compressor.compress(jsonBytes));
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
		try 
		{
			LZ4Factory factory = LZ4Factory.fastestInstance();

			LZ4DecompressorWithLength decompressor = new LZ4DecompressorWithLength(factory.fastDecompressor());
		//	byte[] restored = new byte[2048];
	//		int compressedLength2 = decompressor.decompress(decodeBase64(compressedJson), restored);
			return new String(decompressor.decompress(decodeBase64(compressedJson)));
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
