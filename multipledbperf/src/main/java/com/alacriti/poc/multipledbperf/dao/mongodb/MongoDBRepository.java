package com.alacriti.poc.multipledbperf.dao.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;

@Repository
public interface MongoDBRepository extends  MongoRepository<CustomerFeedVO,Long>
{

}
