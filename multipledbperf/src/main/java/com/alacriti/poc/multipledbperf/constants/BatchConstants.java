/*
 * Created on Jan 6, 2005
 */
package com.alacriti.poc.multipledbperf.constants;
/**
 * @author Kalyana Chillara This class defines the constants that are used for batch file processing
 */
public final class BatchConstants
{
    /**
     * The parameter that is set in the request scope by the BatchRouterServlet when routing the
     * requests
     */
    public static final String AppConfigVO = "APPCONFIGVO";

    public static final String BatchFileInfo = "BATCHFILEINFO";

    public static final String BatchFileConfig = "BATCHFILECONFIG";

    /* The FileName is used by BitalReceive Servlet and Bital Transmitter Servlet */
    public static final String FileName = "FileName";

    /** The FileSize is used by the BitalReceive Servlet */
    public static final String FileSize = "FileSize";

    /** The command thats bening sent */
    public static final String AppCmd = "AppCmd";

    // The RequestType used as constant to get command type
    public static final String RequestType = "RequestType";

    /** Later we should change these string comstants */
    public static final String AuthId = "AuthId";

    public static final String AuthPasswd = "AuthPasswd";

    public static final String ProductId = "AppId";

    public static final String PartnerId = "ClientId";

    public static final String FileType = "FileType";

    public static final String FileId = "FileId";

    public static final String LastModifiedDate = "LastModifiedDate";

    /** Possible values for AppCmd */
    public static final String ListCmd = "List";

    public static final String ResponseListCmd = "ResponseList";

    public static final String ClientListCmd = "ClientList";

    public static final String StatusListCmd = "StatusList";

    public static final String GetFileCmd = "GetFile";

    public static final String RcvSuccessCmd = "RcvSuccess";

    public static final String GetClientFileCmd = "GetClientFile";

    /** The argument to the command being sent */
    public static final String AppCmdArg = "AppCmdArg";

    /** Possible values for AppCmdArg */
    public static final String GetFileCmdArg = "GetFileCmdArg";

    public static final String RcvSuccessArg = "RcvSuccessArg";

    public static final String RcvFailCmd = "RcvFail";

    public static final String HPGToBital = "HB";

    public static final String BitalToHPG = "BH";

    public static final String HPGToClient = "HC";

    // these are added for the hbbr project
    public static final int BusinessPartner = 1;

    public static final int ClientPartner = 2;

    // The file category constants
    public static final int ClientPrtnrInpCtgry = 1;

    public static final int ClientPrtnrResCtgry = 2;

    public static final int BusinessPrtnrInpCtgry = 3;

    public static final int BusinessPrtnrIntResCtgry = 4;

    public static final int BusinessPrtnrDlyResCtgry = 5;

    // status defined for inbound file table
    public static final int InbFileRecvStart = 100;

    public static final int InbFileStored = 200;

    public static final int InbFileCheckStart = 300;

    public static final int InbFileCheckParseEnd = 400;

    // The status codes used for batch files
    public static final int BatchReceived = 401;

    public static final int BatchAccepted = 402;

    public static final int BatchReceiveError = 403;

    public static final int BatchLoadStart = 404;

    public static final int BatchLoadEnd = 405;

    public static final int BatchLoadError = 406;

    public static final int BatchNameStart = 407;

    public static final int BatchNameEnd = 408;

    public static final int BatchNameError = 409;

    public static final int InbFileLoadEnd = 500;

    public static final int InbFileBadConf = 600;

    public static final int InbFileStoreError = 700;

    public static final int InbFileCheckParseError = 800;

    public static final int InbFileLoadError = 900;

    /** These are used by the download client */
    public static final int ClientDownloadSuccess = 14;

    public static final int ClientDownloadFail = 13;

    public static final int ClientToHPGUploadSuccess = 18;

    public static final int ClientToHPGUploadFail = 19;

    // Status used for outbound file table
    public static final int ZeroLength = 100;

    public static final int ParseStart = 1;

    public static final int ParseEnd = 2;

    public static final int FtpStart = 3;

    public static final int FtpSuccess = 4;

    public static final int FtpRetry = 5;

    public static final int FtpFail = 6;

    public static final int FtpWait = 7;

    public static final int DownloadSuccess = 8;

    public static final int DownloadFail = 9;

    public static final int ResAvailable = 10;

    public static final int UploadSuccess = 11;

    public static final int UploadFail = 12;

    public static final int BatchProcessError = 13;

    public static final int BatchDuplicate = 15;

    public static final int BatchError = 17;

    public static final int BatchDecryptError = 31;

    public static final int BatchDecryptStart = 30;

    public static final int BatchDecryptOk = 32;

    public static final int IntResLoadSuccess = 33;

    public static final int IntResLoadFail = 34;

    public static final int BatchUploadOper = 1;
    public static final int BatchDownloadOper = 2;
    public static final int BatchListOper = 3;
    public static final int BatchStatusOper = 4;

    public static final int FILE_NOT_EXIST_STATUS = 700;
    public static final int FILE_FORMAT_ERROR = 406;

    // added as part of ACH Enhancements
    public static final int ACCT_TYPE_BANK = 1;
    public static final int STLMT_RECORD_TYPE_PAYMENT = 1;
    public static final int STLMT_RECORD_TYPE_RETURN = 2;
    public static final int STLMT_RECORD_STATUS_READY = 1;
    public static final int STLMT_RECORD_STATUS_COMPLETE = 2;
    public static final long STLMT_RECORD_ERR_CODE_SUCCESS = 0;

    public static final String SETTLEMENT_FLAG_DIRECT = "D";

    public static final int TXN_PYMNT_SCHLD_TYPE_IMM = 1;

    public static final int TXN_MAIN_TYPE_C2B = 1;
    public static final int TXN_MAIN_TYPE_B2C = 2;
    public static final int TXN_MAIN_TYPE_B2B = 3;
    public static final int TXN_MAIN_TYPE_C2C = 4;
    public static final int TXN_MAIN_TYPE_B2B_STLMT_PARENT_TXN_S2S = 8;
    public static final int TXN_TYPE_REFUND = 3;
    public static final int TXN_TYPE_REFUND_FEE = 4;

    public static final int SETTLEMENT_STEP_TYPE_GROSS = 1;
    public static final int SETTLEMENT_STEP_TYPE_PYMNT = 2;
    public static final int SETTLEMENT_STEP_TYPE_FEE = 3;
    public static final int SETTLEMENT_STEP_TYPE_RETURNS = 4;
    public static final int SETTLEMENT_STEP_TYPE_RETURN_PAYMENT = 5;
    public static final int SETTLEMENT_STEP_TYPE_RETURN_FEE = 6;
    public static final int SETTLEMENT_STEP_TYPE_PARTNER_PAYMENT_FEE = 8;
    public static final int SETTLEMENT_STEP_TYPE_PARTNER_RETURN_FEE = 9;

    public static final int SETTLEMENT_CONFIG_TYPE_CUSTOMER_IN_CUSTOMER_ACH = 1;
    public static final int SETTLEMENT_CONFIG_TYPE_STLMT_IN_CUSTOMER_ACH = 2;
    public static final int SETTLEMENT_CONFIG_TYPE_STLMT_IN_STLMT_ACH = 3;
    public static final int FUND_ACCT_OWNER_TYPE_PERSONAL = 1;

    public static final String STLMT_FLAG_GROSS = "G";
    public static final String STLMT_FLAG_NET = "N";

    public static final String CREDIT_DEBIT_FLAG_CREDIT = "C";
    public static final String CREDIT_DEBIT_FLAG_DEBIT = "D";

    public static final String STLMT_DEBIT_FLAG = "D";
    public static final String STLMT_CREDIT_FLAG = "C";

    public static final int TXN_ID_TYPE_CUSTOMER_LINK = 1;
    public static final int TXN_ID_TYPE_SETTLEMENT_LINK = 2;
    public static final int TXN_ID_TYPE_RETURN_LINK = 3;

    public static final int SETTLEMENT_PROC_TYPE_NET_PAYMENT = 1;
    public static final int SETTLEMENT_PROC_TYPE_NET_RETURN = 4;
    public static final int EMPTY_FILE = 36;

    public static final String ACCT_OWNER_TYPE_BUSINESS = "B";

    public static final String BATCH_AMOUNT = "batch_amt";
    public static final String PROC_ID_LIST = "proc_id_list";

    public static final String COLLAPSE_PAYMENT_TYPE_BATCHES_FALSE = "N";
    public static final String COLLAPSE_PAYMENT_TYPE_BATCHES_TRUE = "Y";

    public static final int TXN_PYMNT_TYPE_ACH = 21;

    public static final int SYSTEM_ERROR = 100;

    public static final int TXN_PYMNT_GROUP_TYPE_ACH = 1;
    public static final int TXN_PYMNT_GROUP_TYPE_CARD = 2;
    public static final int TXN_PYMNT_GROUP_TYPE_CHECK = 3;
    public static final int TXN_PYMNT_SCHLD_TYPE_CONVINIENCE_FEE = 16;
    public static final int TXN_PYMNT_SCHLD_TYPE_REFUND_FEE = 19;
    public static final int TXN_PYMNT_SCHLD_TYPE_INVOICE_PAYMENT = 17;

    // public static final String PRODUCT_ID="PRODUCT_ID";
    public static final int FILE_POST_STATUS = 33;
    public static final int FILE_PRE_STATUS = 2;
    public static final int FILE_CREATION_STATUS = 1;
    public static final int FILE_EMPTY_STATUS = 36;
    public static final int EMPTY_FILE_STATUS = 500;

    public static final String RECORD_TYPE_FILE_HEADER = "FH";
    public static final String RECORD_TYPE_BATCH_HEADER = "BH";

    public static final String RECORD_TYPE_DETAIL = "D";

    public static final String RECORD_TYPE_BATCH_TRAILER = "BT";
    public static final String RECORD_TYPE_FILE_TRAILER = "FT";

    public static final String EVENT_PARAM_FILE_TYPE = "FILE_TYPE";
    public static final String EVENT_PARAM_FILTER_FILE_TYPE = "FILTER_FILE_TYPE";
    public static final String EVENT_PARAM_BIN_FILE_TYPE = "BIN_FILE_TYPE";
    public static final String EVENT_PARAM_ACCOUNT_MASTER_COMPLETE_FILE_TYPE = "ACCOUNT_MASTER_COMPLETE_FILE_TYPE";

    public static final int TXN_PYMNT_SCHLD_TYPE_RECURRING_INSTANCE = 13;
    public static final int TXN_PYMNT_SCHLD_TYPE_AUTOPAY_INSTANCE = 12;
    public static final int TRANSACTION_ENABLED = 1;
    public static final int TXN_PYMNT_SCHLD_TYPE_ONE_TIME = 1;
    public static final int TXN_PYMNT_SCHLD_TYPE_RECURRING_TEMPLATE = 3;
    public static final int TXN_PYMNT_SCHLD_TYPE_AUTOPAY_TEMPLATE = 11;

    public static final int CUST_LOAN_ACCT_TYPE = 2;
    public static final int CUST_LOAN_ACCT_SUB_TYPE = 20;

    public static final String EDMS_RECORD_TYPE_DETAILED_BILL_IMAGE_RECORD = "25";
    public static final String EDMS_RECORD_TYPE_DETAILED_INVOICE_DATA_RECOED = "26";
    public static final String EDMS_RECORD_TYPE_DETAILED_BILL_IMAGE_OR_INVOICE_RECORD = "25";

    public static final int INBOUND_FILE_RECORDS_LOADED = 1;
    public static final int INBOUND_FILE_RECORDS_PROCESSED = 2;
    public static final int INBOUND_FILE_RECORDS_FAILED = 3;
    public static final int INBOUND_FILE_RECORDS_DUPLICATE = 4;
    public static final int HCSC_AMF_FILE_RECORDS_FAILED = 5;

    public static final String EVENT_PARAM_FED_ACH_AGREEMENT_URL = "EVENT_PARAM_FED_ACH_AGREEMENT_URL";
    public static final String EVENT_PARAM_FED_ACH_FILE_DOWNLOAD_URL = "EVENT_PARAM_FED_ACH_FILE_DOWNLOAD_URL";

    public static final String EVENT_PARAM_DATA_SOURCE = "EVENT_PARAM_DATA_SOURCE";
    public static final String VIEWCONFIG_GEN_URL = "VIEWCONFIG_GEN_URL";
    public static final String PARTNER_OPTION = "PARTNER_OPTION";

    public static final String EVENT_PARAM_POP_HOST = "EVENT_PARAM_POP_HOST";
    public static final String EVENT_PARAM_SMTP_HOST = "EVENT_PARAM_SMTP_HOST";
    public static final String EVENT_PARAM_SMTP_USERNAME = "EVENT_PARAM_SMTP_USERNAME";
    public static final String EVENT_PARAM_SMTP_PASSWORD = "EVENT_PARAM_SMTP_PASSWORD";
    public static final String EVENT_PARAM_SMTP_PORT = "EVENT_PARAM_SMTP_PORT";
    public static final String EVENT_PARAM_ENABLE_AUTH = "EVENT_PARAM_ENABLE_AUTH";
    public static final String EVENT_PARAM_ENABLE_TLS = "EVENT_PARAM_ENABLE_TLS";

}
