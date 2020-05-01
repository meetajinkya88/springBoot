package com.alacriti.poc.multipledbperf.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;

public class CustomerRowMapper implements RowMapper<CustomerFeedVO> 
{
    @Override
    public CustomerFeedVO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CustomerFeedVO feedVO = new CustomerFeedVO();
 
    
    	feedVO.setFirstName(rs.getString("fname"));
    	feedVO.setLastName(rs.getString("lname"));
    	feedVO.setZip1(rs.getString("zip_code"));
    	feedVO.setEmailAddr(rs.getString("emailid"));
    	feedVO.setExtCustomerId(new String(rs.getBytes("ext_crypt_customer_id")));
    	feedVO.setUniqueCustomerId(new String(rs.getBytes("uniq_custacctno")));
    	feedVO.setCustomerAccountNumber(new String(rs.getBytes("acct_no")));
    	feedVO.setSsn(new String(rs.getBytes("cust_ssn")));
    	feedVO.setExternalStoragePath(rs.getString("json_path"));
    	feedVO.setPartnerKey(rs.getString("partner_key"));
    	feedVO.setVersion(rs.getInt("version"));
    	feedVO.setJsonText(rs.getString("json_text_str"));
//		feedVO.setJsonText(rs.getString("json_text"));

 
        return feedVO;
    }
}
