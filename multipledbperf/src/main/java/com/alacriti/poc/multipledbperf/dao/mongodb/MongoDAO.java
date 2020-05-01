package com.alacriti.poc.multipledbperf.dao.mongodb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;
import com.mongodb.bulk.BulkWriteResult;

@Component
public class MongoDAO
{
	protected Logger log = LoggerFactory.getLogger(MongoDAO.class);

	@Autowired
	protected MongoDBRepository mongoDbRepository;

	@Autowired
	private MongoTemplate mongoTemplate;


	public CustomerFeedVO getCustomerById(Long feedId)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("feedId").is(feedId));
		return mongoTemplate.findOne(query, CustomerFeedVO.class);
	}

	public void addCustomers(List<CustomerFeedVO> customerFeedList) throws Exception 
	{
		log.info("Saving Customers.");
		BulkOperations bulkOperations =  mongoTemplate.bulkOps(BulkMode.ORDERED,CustomerFeedVO.class);
		for (CustomerFeedVO feedVO : customerFeedList) 
			bulkOperations.insert(feedVO);

		BulkWriteResult result = bulkOperations.execute();

		if (result.getInsertedCount() != customerFeedList.size())
			throw new Exception("customerFeedList size (" + customerFeedList.size() + " ) not equals result size (" + result.getInsertedCount()+ ")");

	}

	public long getFirstInsertedFeedId()
	{
//		Aggregation aggregation = newAggregation(group("_id").min("_id").as("minId"));
//		//Convert the aggregation result into a List
//		AggregationResults<Long> groupResults 
//		= mongoTemplate.aggregate(aggregation, "customerFeedVO",Long.class);
//		List<Long> result = groupResults.getMappedResults();
//
//		if(result != null && !result.isEmpty())
//		{
//			log.info("First Inserted Feed Record Id :" + result.get(0));
//			return result.get(0);
//		}
//		
//		log.info("First Inserted Feed Record Id : 0");

		return 1;


	}
	public long getLastInsertedFeedId()
	{
//		Aggregation aggregation = newAggregation(sort(Sort.Direction.DESC, "_id"));
//		aggregation.limit(1);
		
	//	Aggregation aggregation = newAggregation(group("productId").count().as("total"));
		
	//	Aggregation aggregation = newAggregation(group("_id").max("_id").as("total"));
//		Query baseQuery =  new BasicQuery("{ productId : 2 }");
		//baseQuery.addCriteria(new Criteria("productId").is(2));
//		long count = mongoTemplate.count(baseQuery,"customerFeedVO");
		//Convert the aggregation result into a List
//		AggregationResults<Map> groupResults 
//		= mongoTemplate.aggregate(aggregation, "customerFeedVO",Map.class);
//		List<Map> result = groupResults.getMappedResults();
//
//		if(result != null && !result.isEmpty())
//		{
//			log.info("Last Inserted Feed Record Id :" + result.get(0));
//			return Long.parseLong(result.get(0).get("total").toString());
//
//		}
//			
		
		long count = 0L;
		log.info("Last Inserted Feed Record Id : "+ count);

		return count;

		//		
	}
	
	public long getNextSequenceValue()
	{
		Query query = new Query(Criteria.where("_id"));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        
        return mongoTemplate.findAndModify(query, update,options, Long.class);
	}
	//	public List<CustomerFeedVO> getAllCustomers()
	//	{
	//		log.info("Getting all customers.");
	//		return mongoDbRepository.findAll();
	//	}
	//	
	//	public CustomerFeedVO getCustomer(Long feedId) 
	//	{
	//		log.info("Getting feed ID: {}."+ feedId);
	//		return mongoDbRepository.findById(feedId).get();
	//	}
	//	
	//	public CustomerFeedVO addCustomer(CustomerFeedVO customerFeedVO) 
	//	{
	//		log.info("Saving Customer.");
	//		mongoDbRepository.
	//		return mongoDbRepository.save(customerFeedVO);
	//	}
	//	
	//	public List<CustomerFeedVO> addCustomers(List<CustomerFeedVO> customerFeedList) 
	//	{
	//		log.info("Saving Customers.");
	//		return mongoDbRepository.saveAll(customerFeedList);
	//	}
	//	
	//	public void getLastFeedId()
	//	{
	////		db.d
	////		mongoDbRepository.find().sort(new Document("_id", -1)).first();
	//	}
}
