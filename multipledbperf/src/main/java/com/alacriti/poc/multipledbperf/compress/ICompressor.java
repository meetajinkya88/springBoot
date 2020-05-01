package com.alacriti.poc.multipledbperf.compress;


public interface ICompressor 
{
	public String compress(String unCompressedJson) ;
	
	public String deCompress(String compressedJson);
}
