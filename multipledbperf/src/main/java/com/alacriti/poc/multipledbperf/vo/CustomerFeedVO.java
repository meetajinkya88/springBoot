package com.alacriti.poc.multipledbperf.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class CustomerFeedVO 
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 9163307667915780444L;
	@Id
	protected long feedId;
	protected String firstName;
	protected String middleInitial;
	protected String lastName;
	protected String ssn;
	protected String emailAddr;
	protected String addrLine1;
	protected String addrLine2;
	protected String city;
	protected String stateCode;
	protected String countryCode;
	protected String zip1;
	protected String zip2;
	protected String customerAccountNumber;
	protected int acctType;
	protected int acctSubType;
	protected String issuerStateCode;
	protected double currentBalance;
	protected double creditLimit;
	protected double minPaymentDue;
	protected double pastAmountDue;
	protected double currStatementBal;
	protected String paymentDueDate; // Fomat MM/DD/YYYY
	protected String invoiceNumber;
	protected String invoiceDate; // Fomat MM/DD/YYYY
	protected String eligIndicator;
	protected String acctEligIndicator;
	protected String partnerKey;
	protected String extCustomerId;
	protected byte[] extCustomerIdRAW;
	protected int fileType;
	protected int recordStatus;
	protected long fileId;
	protected long customerFeedRecordId;
	protected long customerId;
	protected int customerStatus;
	protected int loanAcctStatus;
	protected String onlineEnrollmentCode;

	protected String mobilePhone;
	protected String gender;
	protected String dateOfBirth;
	protected String homePhone;
	protected String workPhone;

	protected int batchFlag = 0;
	protected String uniqueCustomerId;

	protected long productId;

	protected long auditEventTxnId;

	protected String skipPymntEligFlag;
	protected double skipPymntFeeAmt;
	protected String skipPymntExpDate;
	protected String skipPymntDates;
	protected String discountEligInd;

	protected String loanAccountId;

	protected String extIssuedLoanAcctId = "-1";

	protected double feeEligibleAmount;

	protected String cardAccountUsageFlag;

	protected String bankAccountUsageFlag;


	protected int errorCode;

	protected String recordKey;

	protected String penaltyEligInd;

	protected String custAcctId;

	protected boolean customerUpdated;

	protected String errorDesc;

	protected long partnerId;

	protected boolean hierLevel1Exists;

	protected boolean hierLevel2Exists;

	protected boolean hierLevel3Exists;

	protected String hierLevel1Value;

	protected String hierLevel2Value;

	protected String hierLevel3Value;

	protected String masterAcctId;

	protected String billingDate;

	protected int cdfFieldCount;
	protected Map<String, String> commonDataFieldsMap;
	protected Map<String, byte[]> secureCommonDataFieldsMap;
	protected String currentBalanceStr;
	protected String creditLimitStr;
	protected String minPaymentDueStr;
	protected String pastAmountDueStr;
	protected String currStatementBalStr;
	protected String feeEligibleAmountStr;
	protected String acctTypeStr;
	protected String acctSubTypeStr;
	protected String skipPymntFeeAmtStr;
	protected String pymtDueDateStr;
	protected String invDateStr;
	protected String skipPymtExpDateStr;
	protected Date pymtDueDate;
	protected Date invDate;
	protected Date skipPymtExpDate;
	protected String mainPartnerKey;
	protected String acctNoHash;
	protected String extCustomerIdHash;
	protected String uniqueCustomerIdHash;
	protected String custSsnHash;

	protected String detailedRecordType;

	protected long feedRecordId;

	protected long createdby;

	protected long auditTxnId;
	protected String feedTableName;
	protected String detailedRecord;

	protected String jsonText;
	protected String externalStoragePath;
	protected String fileName;

	protected int version =1;
	private String absoluteFileName;


	public int encFieldNo = 0;
	public int operation;
	private byte[] customerAccountNumberRAW;
	private byte[] ssnRAW;
	private byte[] uniqueCustomerIdRAW;
	private byte[] jsonTextRAW;
	public static final int ENCRYPTION = 1;
	public static final int DECRYPTION = 2;

	public static final int CUSTOMER_NUM = 1;
	public static final int SSN_NUM = 2;
	public static final int CUSTOMER_ID_NUM = 3;
	public static final int UNIQUE_CUST_ID = 4;
	public static final int JSON_TEXT = 5;

	public String getFirstName() {
		return firstName;
		
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getZip1() {
		return zip1;
	}

	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}

	public String getZip2() {
		return zip2;
	}

	public void setZip2(String zip2) {
		this.zip2 = zip2;
	}

	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public int getAcctType() {
		return acctType;
	}

	public void setAcctType(int acctType) {
		this.acctType = acctType;
	}

	public int getAcctSubType() {
		return acctSubType;
	}

	public void setAcctSubType(int acctSubType) {
		this.acctSubType = acctSubType;
	}

	public String getIssuerStateCode() {
		return issuerStateCode;
	}

	public void setIssuerStateCode(String issuerStateCode) {
		this.issuerStateCode = issuerStateCode;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getMinPaymentDue() {
		return minPaymentDue;
	}

	public void setMinPaymentDue(double minPaymentDue) {
		this.minPaymentDue = minPaymentDue;
	}

	public double getPastAmountDue() {
		return pastAmountDue;
	}

	public void setPastAmountDue(double pastAmountDue) {
		this.pastAmountDue = pastAmountDue;
	}

	public double getCurrStatementBal() {
		return currStatementBal;
	}

	public void setCurrStatementBal(double currStatementBal) {
		this.currStatementBal = currStatementBal;
	}

	public String getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(String paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getEligIndicator() {
		return eligIndicator;
	}

	public void setEligIndicator(String eligIndicator) {
		this.eligIndicator = eligIndicator;
	}

	public String getAcctEligIndicator() {
		return acctEligIndicator;
	}

	public void setAcctEligIndicator(String acctEligIndicator) {
		this.acctEligIndicator = acctEligIndicator;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getExtCustomerId() {
		return extCustomerId;
	}

	public void setExtCustomerId(String extCustomerId) {
		this.extCustomerId = extCustomerId;
	}

	public byte[] getExtCustomerIdRAW() {
		return extCustomerIdRAW;
	}

	public void setExtCustomerIdRAW(byte[] extCustomerIdRAW) {
		this.extCustomerIdRAW = extCustomerIdRAW;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getCustomerFeedRecordId() {
		return customerFeedRecordId;
	}

	public void setCustomerFeedRecordId(long customerFeedRecordId) {
		this.customerFeedRecordId = customerFeedRecordId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public int getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}

	public int getLoanAcctStatus() {
		return loanAcctStatus;
	}

	public void setLoanAcctStatus(int loanAcctStatus) {
		this.loanAcctStatus = loanAcctStatus;
	}

	public String getOnlineEnrollmentCode() {
		return onlineEnrollmentCode;
	}

	public void setOnlineEnrollmentCode(String onlineEnrollmentCode) {
		this.onlineEnrollmentCode = onlineEnrollmentCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public int getBatchFlag() {
		return batchFlag;
	}

	public void setBatchFlag(int batchFlag) {
		this.batchFlag = batchFlag;
	}

	public String getUniqueCustomerId() {
		return uniqueCustomerId;
	}

	public void setUniqueCustomerId(String uniqueCustomerId) {
		this.uniqueCustomerId = uniqueCustomerId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getAuditEventTxnId() {
		return auditEventTxnId;
	}

	public void setAuditEventTxnId(long auditEventTxnId) {
		this.auditEventTxnId = auditEventTxnId;
	}

	public String getSkipPymntEligFlag() {
		return skipPymntEligFlag;
	}

	public void setSkipPymntEligFlag(String skipPymntEligFlag) {
		this.skipPymntEligFlag = skipPymntEligFlag;
	}

	public double getSkipPymntFeeAmt() {
		return skipPymntFeeAmt;
	}

	public void setSkipPymntFeeAmt(double skipPymntFeeAmt) {
		this.skipPymntFeeAmt = skipPymntFeeAmt;
	}

	public String getSkipPymntExpDate() {
		return skipPymntExpDate;
	}

	public void setSkipPymntExpDate(String skipPymntExpDate) {
		this.skipPymntExpDate = skipPymntExpDate;
	}

	public String getSkipPymntDates() {
		return skipPymntDates;
	}

	public void setSkipPymntDates(String skipPymntDates) {
		this.skipPymntDates = skipPymntDates;
	}

	public String getDiscountEligInd() {
		return discountEligInd;
	}

	public void setDiscountEligInd(String discountEligInd) {
		this.discountEligInd = discountEligInd;
	}

	public String getLoanAccountId() {
		return loanAccountId;
	}

	public void setLoanAccountId(String loanAccountId) {
		this.loanAccountId = loanAccountId;
	}

	public String getExtIssuedLoanAcctId() {
		return extIssuedLoanAcctId;
	}

	public void setExtIssuedLoanAcctId(String extIssuedLoanAcctId) {
		this.extIssuedLoanAcctId = extIssuedLoanAcctId;
	}

	public double getFeeEligibleAmount() {
		return feeEligibleAmount;
	}

	public void setFeeEligibleAmount(double feeEligibleAmount) {
		this.feeEligibleAmount = feeEligibleAmount;
	}

	public String getCardAccountUsageFlag() {
		return cardAccountUsageFlag;
	}

	public void setCardAccountUsageFlag(String cardAccountUsageFlag) {
		this.cardAccountUsageFlag = cardAccountUsageFlag;
	}

	public String getBankAccountUsageFlag() {
		return bankAccountUsageFlag;
	}

	public void setBankAccountUsageFlag(String bankAccountUsageFlag) {
		this.bankAccountUsageFlag = bankAccountUsageFlag;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getRecordKey() {
		return recordKey;
	}

	public void setRecordKey(String recordKey) {
		this.recordKey = recordKey;
	}

	public String getPenaltyEligInd() {
		return penaltyEligInd;
	}

	public void setPenaltyEligInd(String penaltyEligInd) {
		this.penaltyEligInd = penaltyEligInd;
	}

	public String getCustAcctId() {
		return custAcctId;
	}

	public void setCustAcctId(String custAcctId) {
		this.custAcctId = custAcctId;
	}

	public boolean isCustomerUpdated() {
		return customerUpdated;
	}

	public void setCustomerUpdated(boolean customerUpdated) {
		this.customerUpdated = customerUpdated;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	public boolean isHierLevel1Exists() {
		return hierLevel1Exists;
	}

	public void setHierLevel1Exists(boolean hierLevel1Exists) {
		this.hierLevel1Exists = hierLevel1Exists;
	}

	public boolean isHierLevel2Exists() {
		return hierLevel2Exists;
	}

	public void setHierLevel2Exists(boolean hierLevel2Exists) {
		this.hierLevel2Exists = hierLevel2Exists;
	}

	public boolean isHierLevel3Exists() {
		return hierLevel3Exists;
	}

	public void setHierLevel3Exists(boolean hierLevel3Exists) {
		this.hierLevel3Exists = hierLevel3Exists;
	}

	public String getHierLevel1Value() {
		return hierLevel1Value;
	}

	public void setHierLevel1Value(String hierLevel1Value) {
		this.hierLevel1Value = hierLevel1Value;
	}

	public String getHierLevel2Value() {
		return hierLevel2Value;
	}

	public void setHierLevel2Value(String hierLevel2Value) {
		this.hierLevel2Value = hierLevel2Value;
	}

	public String getHierLevel3Value() {
		return hierLevel3Value;
	}

	public void setHierLevel3Value(String hierLevel3Value) {
		this.hierLevel3Value = hierLevel3Value;
	}

	public String getMasterAcctId() {
		return masterAcctId;
	}

	public void setMasterAcctId(String masterAcctId) {
		this.masterAcctId = masterAcctId;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public int getCdfFieldCount() {
		return cdfFieldCount;
	}

	public void setCdfFieldCount(int cdfFieldCount) {
		this.cdfFieldCount = cdfFieldCount;
	}

	public Map<String, String> getCommonDataFieldsMap() {
		return commonDataFieldsMap;
	}

	public void setCommonDataFieldsMap(Map<String, String> commonDataFieldsMap) {
		this.commonDataFieldsMap = commonDataFieldsMap;
	}

	public Map<String, byte[]> getSecureCommonDataFieldsMap() {
		return secureCommonDataFieldsMap;
	}

	public void setSecureCommonDataFieldsMap(Map<String, byte[]> secureCommonDataFieldsMap) {
		this.secureCommonDataFieldsMap = secureCommonDataFieldsMap;
	}

	public String getCurrentBalanceStr() {
		return currentBalanceStr;
	}

	public void setCurrentBalanceStr(String currentBalanceStr) {
		this.currentBalanceStr = currentBalanceStr;
	}

	public String getCreditLimitStr() {
		return creditLimitStr;
	}

	public void setCreditLimitStr(String creditLimitStr) {
		this.creditLimitStr = creditLimitStr;
	}

	public String getMinPaymentDueStr() {
		return minPaymentDueStr;
	}

	public void setMinPaymentDueStr(String minPaymentDueStr) {
		this.minPaymentDueStr = minPaymentDueStr;
	}

	public String getPastAmountDueStr() {
		return pastAmountDueStr;
	}

	public void setPastAmountDueStr(String pastAmountDueStr) {
		this.pastAmountDueStr = pastAmountDueStr;
	}

	public String getCurrStatementBalStr() {
		return currStatementBalStr;
	}

	public void setCurrStatementBalStr(String currStatementBalStr) {
		this.currStatementBalStr = currStatementBalStr;
	}

	public String getFeeEligibleAmountStr() {
		return feeEligibleAmountStr;
	}

	public void setFeeEligibleAmountStr(String feeEligibleAmountStr) {
		this.feeEligibleAmountStr = feeEligibleAmountStr;
	}

	public String getAcctTypeStr() {
		return acctTypeStr;
	}

	public void setAcctTypeStr(String acctTypeStr) {
		this.acctTypeStr = acctTypeStr;
	}

	public String getAcctSubTypeStr() {
		return acctSubTypeStr;
	}

	public void setAcctSubTypeStr(String acctSubTypeStr) {
		this.acctSubTypeStr = acctSubTypeStr;
	}

	public String getSkipPymntFeeAmtStr() {
		return skipPymntFeeAmtStr;
	}

	public void setSkipPymntFeeAmtStr(String skipPymntFeeAmtStr) {
		this.skipPymntFeeAmtStr = skipPymntFeeAmtStr;
	}

	public String getPymtDueDateStr() {
		return pymtDueDateStr;
	}

	public void setPymtDueDateStr(String pymtDueDateStr) {
		this.pymtDueDateStr = pymtDueDateStr;
	}

	public String getInvDateStr() {
		return invDateStr;
	}

	public void setInvDateStr(String invDateStr) {
		this.invDateStr = invDateStr;
	}

	public String getSkipPymtExpDateStr() {
		return skipPymtExpDateStr;
	}

	public void setSkipPymtExpDateStr(String skipPymtExpDateStr) {
		this.skipPymtExpDateStr = skipPymtExpDateStr;
	}

	public Date getPymtDueDate() {
		return pymtDueDate;
	}

	public void setPymtDueDate(Date pymtDueDate) {
		this.pymtDueDate = pymtDueDate;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public Date getSkipPymtExpDate() {
		return skipPymtExpDate;
	}

	public void setSkipPymtExpDate(Date skipPymtExpDate) {
		this.skipPymtExpDate = skipPymtExpDate;
	}

	public String getMainPartnerKey() {
		return mainPartnerKey;
	}

	public void setMainPartnerKey(String mainPartnerKey) {
		this.mainPartnerKey = mainPartnerKey;
	}

	public String getAcctNoHash() {
		return acctNoHash;
	}

	public void setAcctNoHash(String acctNoHash) {
		this.acctNoHash = acctNoHash;
	}

	public String getExtCustomerIdHash() {
		return extCustomerIdHash;
	}

	public void setExtCustomerIdHash(String extCustomerIdHash) {
		this.extCustomerIdHash = extCustomerIdHash;
	}

	public String getUniqueCustomerIdHash() {
		return uniqueCustomerIdHash;
	}

	public void setUniqueCustomerIdHash(String uniqueCustomerIdHash) {
		this.uniqueCustomerIdHash = uniqueCustomerIdHash;
	}

	public String getCustSsnHash() {
		return custSsnHash;
	}

	public void setCustSsnHash(String custSsnHash) {
		this.custSsnHash = custSsnHash;
	}

	public String getDetailedRecordType() {
		return detailedRecordType;
	}

	public void setDetailedRecordType(String detailedRecordType) {
		this.detailedRecordType = detailedRecordType;
	}

	public long getFeedRecordId() {
		return feedRecordId;
	}

	public void setFeedRecordId(long feedRecordId) {
		this.feedRecordId = feedRecordId;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public long getAuditTxnId() {
		return auditTxnId;
	}

	public void setAuditTxnId(long auditTxnId) {
		this.auditTxnId = auditTxnId;
	}

	public void setDetailedRecord(String entryDetailRecord) {
		this.detailedRecord = entryDetailRecord;		
	}

	public void setFeedTableName(String feedTableName) {
		this.feedTableName = feedTableName;;		
	}
	public String getFeedTableName() {
		return feedTableName;
	}

	public String getDetailedRecord() {
		return detailedRecord;
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}



	public String getExternalStoragePath() {
		return externalStoragePath;
	}

	public void setExternalStoragePath(String externalStoragePath) {
		this.externalStoragePath = externalStoragePath;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public void setAbsoluteFileName(String absoluteFileName)
	{
		this.absoluteFileName = absoluteFileName;		
	}

	public String getAbsoluteFileName()
	{
		return absoluteFileName;
	}

	public byte[] getCustomerAccountNumberRAW() {
		return customerAccountNumberRAW;
	}

	public void setCustomerAccountNumberRAW(byte[] customerAccountNumberRAW) {
		this.customerAccountNumberRAW = customerAccountNumberRAW;
	}

	public byte[] getSsnRAW() {
		return ssnRAW;
	}

	public void setSsnRAW(byte[] ssnRAW) {
		this.ssnRAW = ssnRAW;
	}

	public byte[] getUniqueCustomerIdRAW() {
		return uniqueCustomerIdRAW;
	}

	public void setUniqueCustomerIdRAW(byte[] uniqueCustomerIdRAW) {
		this.uniqueCustomerIdRAW = uniqueCustomerIdRAW;
	}

	public byte[] getJsonTextRAW() {
		return jsonTextRAW;
	}

	public void setJsonTextRAW(byte[] jsonTextRAW) {
		this.jsonTextRAW = jsonTextRAW;
	}

	public int getEncFieldNo() {
		return encFieldNo;
	}

	public void setEncFieldNo(int encFieldNo) {
		this.encFieldNo = encFieldNo;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public long getFeedId() {
		return feedId;
	}

	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}

//	public byte[] getCryptData()
//	{
//		byte[] val = null;
//
//
//		if (operation == ENCRYPTION)
//		{
//			if (encFieldNo == CUSTOMER_NUM)
//			{
//				if (!Validations.isEmpty(customerAccountNumber))
//					val = customerAccountNumber.getBytes();
//			}
//			else if (encFieldNo == SSN_NUM)
//			{
//				if (!Validations.isEmpty(ssn))
//					val = ssn.getBytes();
//			}
//			else if (encFieldNo == CUSTOMER_ID_NUM)
//			{
//				if (!Validations.isEmpty(extCustomerId))
//					val = extCustomerId.getBytes();
//			}
//			else if (encFieldNo == UNIQUE_CUST_ID)
//			{
//				if (!Validations.isEmpty(ssn))
//					val = uniqueCustomerId.getBytes();
//			}
//			else if (encFieldNo == JSON_TEXT)
//			{
//				if (!Validations.isEmpty(extCustomerId))
//					val = jsonText.getBytes();
//			}
//			else
//			{
//
//				throw new IllegalStateException("getCryptData() in CustomerFeedVO had bad encFieldNo " + encFieldNo
//						+ " Throwing Exception explictly");
//			}
//		}
//		else
//		{
//			throw new IllegalStateException("getCryptData() in CustomerFeedVO had bad operation " + operation
//					+ " Throwing Exception explictly");
//		}
//
//		return val;
//	}

	
//	@Override
//	public void setCryptData(byte[] data)
//	{
//		 if (operation == ENCRYPTION)
//	        {
//	            if (encFieldNo == CUSTOMER_NUM)
//	            {
//	                customerAccountNumberRAW = data;
//	            }
//	            else if (encFieldNo == SSN_NUM)
//	            {
//	                ssnRAW = data;
//	            }
//	            else if (encFieldNo == CUSTOMER_ID_NUM)
//	            {
//	                extCustomerIdRAW = data;
//	            }
//	            else if (encFieldNo == UNIQUE_CUST_ID)
//	            {
//	                uniqueCustomerIdRAW = data;
//	            }
//	            else if (encFieldNo == JSON_TEXT)
//	            {
//	                jsonTextRAW = data;
//	            }
//	            else
//	            {
//	                throw new IllegalStateException("setCryptData(bytes[]) in CustomerFeedVO had bad encFieldNo "
//	                        + encFieldNo + " Throwing Exception explictly");
//	            }
//	        }
//	        else
//	        {
//	            throw new IllegalStateException("setCryptData(bytes[]) in CustomerFeedVO had bad operation " + operation
//	                    + " Throwing Exception explictly");
//	        }
//	}


}