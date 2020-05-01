package com.alacriti.poc.multipledbperf.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.service.MultpleDBPerfService;
import com.alacriti.poc.multipledbperf.vo.PersistRecordResponse;
import com.alacriti.poc.multipledbperf.vo.ReadRecordResponse;

@RestController(Constants.RestURLConstants.APP_CONTEXT_URL)
public class MultipleDBPerfResource 
{
	@Autowired
	MultpleDBPerfService service;
	
	public MultipleDBPerfResource()
	{
	}
	
	@RequestMapping(Constants.RestURLConstants.PERSIST_RECORDS)
	public PersistRecordResponse persistRecords(@RequestParam Map<String, String> paramMap) 
	{
		return service.persistRecord(paramMap);
	}
	
	@RequestMapping(Constants.RestURLConstants.READ_RECORD)
	public ReadRecordResponse readRecord(@RequestParam Map<String, String> paramMap) 
	{
		return service.readRecord(paramMap);
	}
	
	@RequestMapping(Constants.RestURLConstants.UPDATE_RECORDS)
	public PersistRecordResponse UpdateRecord(@RequestParam Map<String, String> paramMap) 
	{
		return service.updateRecord(paramMap);
	}

}
