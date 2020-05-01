package com.alacriti.poc.multipledbperf.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alacriti.poc.multipledbperf.vo.CustomerFeedMetaData;

public class CustomerFeedMetaDataMapper implements RowMapper<CustomerFeedMetaData> 
{
	
    public CustomerFeedMetaDataMapper() 
    {
		super();
	}

	@Override
    public CustomerFeedMetaData mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerFeedMetaData feedVO = new CustomerFeedMetaData();
 
    
		feedVO.setCount(rs.getLong("count"));
		feedVO.setMaxFeedId(rs.getLong("max_feed_id"));
		feedVO.setMinFeedId(rs.getLong("min_feed_id"));
 
        return feedVO;
    }
}
