package com.alacriti.poc.multipledbperf.compress;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("DefaultCompressor")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class DefaultCompressor implements ICompressor 
{

	@Override
	public String compress(String unCompressedJson)
	{
		return unCompressedJson;
	}

	@Override
	public String deCompress(String compressedJson) 
	{
		return compressedJson;
	}

}
