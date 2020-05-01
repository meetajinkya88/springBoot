package com.alacriti.poc.multipledbperf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.dao.mapper.CustomerFeedMetaDataMapper;
import com.alacriti.poc.multipledbperf.dao.mapper.CustomerRowMapper;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedMetaData;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;

@Repository
public class CustomerRepository
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Add new customer detail records
	public void addCustomer(ArrayList<CustomerFeedVO> recordList, String persistenceType) throws SQLException
	{
		String sqlQuery = null;

		if(persistenceType.equalsIgnoreCase(Constants.PERSISTENCE_TYPE_MYSQL))
			sqlQuery =	Constants.SQLConstants.ADD_CUSTOMER_RECORD_MYSQL;
		else
			sqlQuery =	Constants.SQLConstants.ADD_CUSTOMER_RECORD_POSGRESSQL;

		jdbcTemplate.batchUpdate( sqlQuery, new BatchPreparedStatementSetter()
		{



			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				CustomerFeedVO customer = recordList.get(i);
				ps.setString(1, customer.getFirstName());
				ps.setString(2, customer.getLastName());
				ps.setString(3, customer.getZip1());

				ps.setString(4, customer.getEmailAddr());
				ps.setString(5, customer.getExtCustomerId());
				//	ps.setString(5, customer.getExtCustomerIdRAW().toString());
				ps.setString(6, customer.getUniqueCustomerId());
				//	ps.setString(6, customer.getUniqueCustomerIdRAW().toString());
				ps.setString(7, customer.getCustomerAccountNumber());
				//	ps.setString(7, customer.getCustomerAccountNumberRAW().toString());
				ps.setString(8, customer.getSsn());
				//	ps.setString(8, customer.getSsnRAW().toString());

				ps.setString(9, customer.getAbsoluteFileName());
				ps.setString(10, customer.getPartnerKey());
				ps.setInt(11, customer.getVersion());

				if(persistenceType.equalsIgnoreCase(Constants.PERSISTENCE_TYPE_MYSQL))
					ps.setString(12, customer.getJsonText());
				else
					ps.setObject(12, customer.getJsonText());
				//ps.setObject(12, customer.getJsonText());

			}

			@Override
			public int getBatchSize() {
				return recordList.size();
			}
		});
	}

	public CustomerFeedVO getCustomerFeedRecord(long feedId) 
	{
		return  jdbcTemplate.queryForObject(
				Constants.SQLConstants.GET_CUSTOMER_FEED_RECORD_WITH_COMPRESSION,  new Object[] { feedId },new CustomerRowMapper());
	}

	public CustomerFeedMetaData getCustFeedMetaData() 
	{
		return  jdbcTemplate.queryForObject(
				Constants.SQLConstants.GET_CUSTOMER_FEED_META_DATA,  new CustomerFeedMetaDataMapper());
	}

	public List<String> getAccounts(long startIndex, long endIndex) 
	{
		return  jdbcTemplate.query(
				Constants.SQLConstants.GET_CUSTOMER_ACCOUNTS,new Object[] { startIndex,endIndex }, new ResultSetExtractor<List<String>>() 
				{
					@Override
					public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException 
					{
						List<String> accounts = new ArrayList<>();

						while(rs.next())
							accounts.add(rs.getString(1));

						return accounts;
					}
				} );

	}

	public void updateCustomer(List<String> accountBatch,String persistenceType)
	{
		String sqlQuery = null;

		if(persistenceType.equalsIgnoreCase(Constants.PERSISTENCE_TYPE_MYSQL))
			sqlQuery =	Constants.SQLConstants.UPDATE_CUSTOMER_RECORD_MYSQL;
		else
			sqlQuery =	Constants.SQLConstants.UPDATE_CUSTOMER_RECORD_POSGRESQL;

		jdbcTemplate.batchUpdate( sqlQuery, new BatchPreparedStatementSetter()
		{

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{

				ps.setString(1, "fname1");
				ps.setString(2, "lname");
				ps.setString(3, "abc@alacriti.com");

				ps.setString(4, "12345");
				ps.setString(5, "ext_crypt_id");
				ps.setString(6, "ssn");

				if(persistenceType.equalsIgnoreCase(Constants.PERSISTENCE_TYPE_MYSQL))
					ps.setString(7,  Constants.SQLConstants.SAMPLE_JSON_TEXT);
				else
					ps.setObject(7, Constants.SQLConstants.SAMPLE_JSON_TEXT);

				ps.setString(8, accountBatch.get(i));

			}

			@Override
			public int getBatchSize() {
				return accountBatch.size();
			}
		});
	}
}
