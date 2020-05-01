package com.alacriti.poc.multipledbperf.constants;

public class Constants 
{
	
	public static final String NULL = null;
	public static final String EMPTY_STRING = "";
	public static final String NA_STRING = "N/A";
	public static final long THREAD_INITIAL_DELAY = 2;
	public static final long THREAD_DELAY = 50;

	// persistenceType: 1 - mysql , 2 - mysql+ s3, 3- posgres, 4 - posgres + s3 , 5 - mongo

	public static final String PERSISTENCE_TYPE_MYSQL= "1";
	public static final String PERSISTENCE_TYPE_MYSQL_S3= "2";
	public static final String PERSISTENCE_TYPE_POSGRES= "3";
	public static final String PERSISTENCE_TYPE_POSGRES_S3= "4";
	public static final String PERSISTENCE_TYPE_MONGO= "5";
		
	public static final String PARAM_COMPRESSION_TYPE_DEFLATOR = "1";
	public static final String PARAM_COMPRESSION_TYPE_LZ4 = "2";

	
	public class ParamConstants
	{
		public static final String PARAM_FILE="file";
		public static final String PARAM_DATA_SOURCE="datasource";
		public static final String PARAM_THREAD_COUNT="threadCount";
		public static final String PARAM_BATCH_SIZE="batchSize";

		public static final String PARAM_DIR_PREFIX = "prefix";
		public static final String PARAM_RUN_TIME="runTime";
		public static final String PARAM_ENCRYPTION_REQUIRED="encryption";
		
		// persistenceType: 1 - mysql , 2 - mysql+ s3, 3- posgres, 3 - posgres + s3 , 4 - mongo
		public static final String PARAM_PERSISTENCE_TYPE="persistenceType";
		public static final String PARAM_COMPRESSION_TYPE = "compressionType";

		public static final String PARAM_UPDATE_RECORD_COUNT="updateRecordCount";

	}
	
	public class RestURLConstants
	{

		public static final String PERSIST_RECORDS = "/persistRecord";
		public static final String APP_CONTEXT_URL = "/multipleDataSourcePerf";
		
		public static final String READ_RECORD = "/readRecord";
		public static final String UPDATE_RECORDS = "/updateRecord";
	}
	
	public class SQLConstants
	{
	
//		public static final String ADD_CUSTOMER_RECORD_SQL = "insert into poc_ext_feed_tbl  (fname,lname, zip_code ,emailid, ext_crypt_customer_id ,uniq_custacctno, acct_no , cust_ssn , json_path, partner_key,version,creation_time, last_modified_time) " + 
//				" values(?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP) "
		   public static final String ADD_CUSTOMER_RECORD_MYSQL = "insert into poc_ext_feed_tbl  (fname,lname, zip_code ,emailid, ext_crypt_customer_id ,uniq_custacctno, acct_no , cust_ssn , json_path, partner_key,version,creation_time, last_modified_time,json_text) " + 
					" values(?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP,?) ";
				//+ " ON DUPLICATE KEY UPDATE fname = ? , lname = ? , zip_code = ? , emailid = ? , ext_crypt_customer_id = ? ,"
			//	+ " acct_no = ? , cust_ssn = ? , json_path = ? , partner_key = ?,version = version + 1 , last_modified_time = CURRENT_TIMESTAMP , json_text = ? ";
		   
		   public static final String GET_CUSTOMER_FEED_RECORD = " select fname,lname, "
		   		+ " zip_code ,emailid, ext_crypt_customer_id ,uniq_custacctno, acct_no , "
		   		+ " cust_ssn , json_path, partner_key,version, json_text from poc_ext_feed_tbl "
		   		+ " where feed_id = ? ";

		public static final String GET_CUSTOMER_FEED_META_DATA = " SELECT min(feed_id) as min_feed_id, "
				+ " max(feed_id) as max_feed_id , count(*) as count FROM poc_ext_feed_tbl ";	
		
		   public static final String ADD_CUSTOMER_RECORD_POSGRESSQL = "insert into poc_ext_feed_tbl  (fname,lname, zip_code ,emailid, ext_crypt_customer_id ,uniq_custacctno, acct_no , cust_ssn , json_path, partner_key,version,creation_time, last_modified_time,json_text) " + 
					" values(?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP,?::JSON) ";
	
		   
		   public static final String ADD_CUSTOMER_RECORD_POSGRESSQL_WITH_COMPRESSION = "insert into poc_ext_feed_tbl  (fname,lname, zip_code ,emailid, ext_crypt_customer_id ,uniq_custacctno, acct_no , cust_ssn , json_path, partner_key,version,creation_time, last_modified_time,json_text_str) " + 
					" values(?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP,?) ";
	
		   public static final String GET_CUSTOMER_FEED_RECORD_WITH_COMPRESSION = " select fname,lname, "
			   		+ " zip_code ,emailid, ext_crypt_customer_id ,uniq_custacctno, acct_no , "
			   		+ " cust_ssn , json_path, partner_key,version, json_text_str from poc_ext_feed_tbl "
			   		+ " where feed_id = ? ";

			public static final String GET_CUSTOMER_ACCOUNTS = " SELECT acct_no "
					+ " FROM poc_ext_feed_tbl where feed_id between ? and ? ";	
		
			   public static final String UPDATE_CUSTOMER_RECORD_MYSQL = "UPDATE poc_ext_feed_tbl " + 
			   		" set fname = ? , " + 
			   		"     lname = ? , " + 
			   		"     emailid = ? , " + 
			   		"     zip_code = ? , " + 
			   		"     ext_crypt_customer_id = ? ," + 
			   		"     cust_ssn = ? , " + 
			   		"     json_text = ? , " + 
			   		"     last_modified_time = now() " + 
			   		"   where acct_no = ? ";
			   
			   public static final String UPDATE_CUSTOMER_RECORD_POSGRESQL = "UPDATE poc_ext_feed_tbl " + 
				   		" set fname = ? , " + 
				   		"     lname = ? , " + 
				   		"     emailid = ? , " + 
				   		"     zip_code = ? , " + 
				   		"     ext_crypt_customer_id = ? ," + 
				   		"     cust_ssn = ? , " + 
				   		"     json_text = ?::JSON , " + 
				   		"     last_modified_time = now() " + 
				   		" where acct_no = ? ";

			public static final String SAMPLE_JSON_TEXT = "{\"ssn\": \"983591083\", \"city\": \"CITY_A51B08\", \"zip1\": \"905493156\", \"zip2\": null, \"feedId\": 0, \"fileId\": 2, \"gender\": null, \"ssnRAW\": \"OTgzNTkxMDgz\", \"invDate\": null, \"version\": 1, \"acctType\": 2, \"fileName\": null, \"fileType\": 1234, \"jsonText\": null, \"lastName\": \"LN82DE542F20\", \"addrLine1\": \"Addr1_50428CD168\", \"addrLine2\": \"Addr2_12D130019B\", \"batchFlag\": 0, \"createdby\": 0, \"emailAddr\": \"ajinkyab@gmail.com\", \"errorCode\": 0, \"errorDesc\": null, \"firstName\": \"FN8CF06CC24D\", \"homePhone\": \"123456789\", \"operation\": 0, \"partnerId\": 0, \"productId\": 2, \"recordKey\": null, \"stateCode\": \"NJ\", \"workPhone\": null, \"acctNoHash\": null, \"auditTxnId\": 0, \"custAcctId\": null, \"customerId\": 0, \"encFieldNo\": 0, \"invDateStr\": null, \"partnerKey\": \"pkey\", \"acctSubType\": 20, \"acctTypeStr\": null, \"billingDate\": \"20160801\", \"countryCode\": \"USA\", \"creditLimit\": 0, \"custSsnHash\": null, \"dateOfBirth\": \"19880424\", \"invoiceDate\": null, \"jsonTextRAW\": null, \"mobilePhone\": null, \"pymtDueDate\": null, \"feedRecordId\": 0, \"masterAcctId\": \"mast_acct_id_AA9B579A1BFFDAE\", \"recordStatus\": 1, \"cdfFieldCount\": 9, \"eligIndicator\": \"E\", \"extCustomerId\": \"690782021\", \"feedTableName\": null, \"invoiceNumber\": null, \"loanAccountId\": null, \"middleInitial\": null, \"minPaymentDue\": 0, \"pastAmountDue\": 0, \"acctSubTypeStr\": null, \"creditLimitStr\": null, \"currentBalance\": 0, \"customerStatus\": 0, \"detailedRecord\": \"21pkey                                              acctNo_2B2909DAF2AC91203FCF     690782021                       onlineEnroll_FFE7EFEDDF47E09    mast_acct_id_AA9B579A1BFFDAE    NYhier_l1_F9B3B144hier_l2_7C950EDChier_l3_80FE4405FN8CF06CC24D                    LN82DE542F20                    983591083      19880424ajinkyab@gmail.com                                                                                  123456789 Addr1_50428CD168                                  Addr2_12D130019B                                  CITY_A51B08         NJUSA905493156 +00000000177.81+00000000334.51+00000000225.45+00000000625.422016073020160801EYEE                                                                                                            009cdf001cdf001_682776A5DD888520395D4A881                                cdf002cdf002_3804038C5DFBC457E41F0C687                                cdf003cdf003_61D4140E548AB7FF5502512C4                                cdf004cdf004_226AFAF629C5928CC94AA30D9                                cdf005cdf005_DAF37FEDBF42C1709D4D3D4E2                                scdf01scdf01_47262B5AD38683CB562854E4A                                scdf02scdf02_1136EE804E442617BA4AF45AD                                scdf03scdf03_A8991EBB911F3DB6659DC937                                 \", \"loanAcctStatus\": 0, \"mainPartnerKey\": \"pkey\", \"paymentDueDate\": \"20160730\", \"penaltyEligInd\": null, \"pymtDueDateStr\": null, \"skipPymntDates\": null, \"auditEventTxnId\": 0, \"customerUpdated\": false, \"discountEligInd\": \"Y\", \"hierLevel1Value\": \"hier_l1_F9B3B144\", \"hierLevel2Value\": \"hier_l2_7C950EDC\", \"hierLevel3Value\": \"hier_l3_80FE4405\", \"issuerStateCode\": \"NY\", \"skipPymntFeeAmt\": 0, \"skipPymtExpDate\": null, \"absoluteFileName\": null, \"currStatementBal\": 0, \"extCustomerIdRAW\": \"NjkwNzgyMDIx\", \"hierLevel1Exists\": false, \"hierLevel2Exists\": false, \"hierLevel3Exists\": false, \"minPaymentDueStr\": \"+00000000225.45\", \"pastAmountDueStr\": \"+00000000625.42\", \"skipPymntExpDate\": null, \"uniqueCustomerId\": \"acctNo_2B2909DAF2AC91203FCF\", \"acctEligIndicator\": \"\", \"currentBalanceStr\": \"+00000000334.51\", \"extCustomerIdHash\": null, \"feeEligibleAmount\": 0, \"skipPymntEligFlag\": null, \"detailedRecordType\": null, \"skipPymntFeeAmtStr\": null, \"skipPymtExpDateStr\": null, \"commonDataFieldsMap\": {\"cdf001\": \"cdf001_682776A5DD888520395D4A881\", \"cdf002\": \"cdf002_3804038C5DFBC457E41F0C687\", \"cdf003\": \"cdf003_61D4140E548AB7FF5502512C4\", \"cdf004\": \"cdf004_226AFAF629C5928CC94AA30D9\", \"cdf005\": \"cdf005_DAF37FEDBF42C1709D4D3D4E2\", \"scdf01\": \"scdf01_47262B5AD38683CB562854E4A\", \"scdf02\": \"scdf02_1136EE804E442617BA4AF45AD\", \"scdf03\": \"scdf03_A8991EBB911F3DB6659DC937\"}, \"currStatementBalStr\": \"+00000000177.81\", \"extIssuedLoanAcctId\": \"-1\", \"externalStoragePath\": null, \"uniqueCustomerIdRAW\": \"YWNjdE5vXzJCMjkwOURBRjJBQzkxMjAzRkNG\", \"bankAccountUsageFlag\": \"E\", \"cardAccountUsageFlag\": \"E\", \"customerFeedRecordId\": 0, \"feeEligibleAmountStr\": null, \"onlineEnrollmentCode\": \"onlineEnroll_FFE7EFEDDF47E09\", \"uniqueCustomerIdHash\": null, \"customerAccountNumber\": \"acctNo_2B2909DAF2AC91203FCF\", \"customerAccountNumberRAW\": \"YWNjdE5vXzJCMjkwOURBRjJBQzkxMjAzRkNG\", \"secureCommonDataFieldsMap\": null}"; 
					
		
	}
	
	public class PropertyConstants
	{
		public final static String PARAM_AWS_S3_BUCKET_NAME="com.alacriti.aws.s3.bucketname";
		public final static String PARAM_AWS_S3_REGION_NAME="com.alacriti.aws.s3.region";
	}
	
}
