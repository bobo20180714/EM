package com.em.common.consts;

public interface RestCommonConsts {
	
	//########################################################
	//#														 #
	//#						Common  						 #												
	//#														 #
	//########################################################	
	/**
	 * underline
	 */
	public static final String C_UNDERLINE					=		"_";
	/*
	 * slash
	 */
	public static final String C_SLASH						=		"/";
	/*
	 * back slash
	 */
	public static final String C_BACK_SLASH					=		"\\";
	/*
	 * Question mark
	 */
	public static final String C_QUESTION_MARK				=		"?";
	/*
	 * ampersand
	 */
	public static final String C_AMPER_AND					=		"&";
	/*
	 * EQUAL
	 */
	public static final String C_EQUAL						=		"=";
	/*
	 * YES
	 */
	public static final String C_Y							=		"1";
	/*
	 * NO
	 */
	public static final String C_N							=		"0";
	
	/*
	 * APP TYPE --  WEB
	 */
	public static final String C_APP_TYPE_WEB 				= 		"1";
	
	/*
	 * COMMA
	 */
	public static final String C_COMMA		 				= 		",";
	
	//########################################################
	//#														 #
	//#						Return Type  					 #												
	//#														 #
	//########################################################	
	
	/**
	 * default
	 */
	public static final String RT_DEFAULT					=		"TEXT";  //text of string
	
	/**
	 * json
	 */
	public static final String RT_JSON						=		"JSON";
	
	/**
	 * xml
	 */
	public static final String RT_XML						=		"XML";  
	
	/**
	 * image
	 */
	public static final String RT_IMAGE						=		"IMAGE";  
	
	/**
	 * url address
	 */
	public static final String RT_ADDRESS					=		"ADDRESS";  
	
	
	//########################################################
	//#														 #
	//#						URL Related						 #												
	//#														 #
	//########################################################
	
	/**
	 * 用户名
	 */
	public static final String SESSION_INFO_TO_ADD_TO_COOKIE		=		"sessionInfoToAddToCookie";
	
	/**
	 * 用来区分验证码请求所带的自定义数字
	 */
	public static final String VCODE_USER_SN		=		"usn-key";
	
	/**
	 * 用户名
	 */
	public static final String TOKEN_REQUEST_USERID			=		"userid";
	
	/**
	 * 密码
	 */
	public static final String TOKEN_REQUEST_PASSWORD		=		"password";
	
	/**
	 * token
	 */
	public static final String TOKEN_REQUEST_SEP			=		"token";
	
	/**
	 * empty
	 */
	public static final String EMPTY						=		"";
		
	/**
	 * url of sign
	 */
	public static final String URL_SIGN						=		"sign";
	
	/**
	 * url of timestamp
	 */
	public static final String TIME_STAMP					=		"timestamp";
	
	/**
	 * token expire time
	 */
	public static final String TOKEN_EXPIRE_TIME			=		"expireTime";
	
//	/**
//	 * key pair
//	 */
//	public static final String KEY_PAIR						=		"KEY_PAIR";
	
	//########################################################
	//#														 #
	//#						DB Related						 #												
	//#														 #
	//########################################################
	
	/*
	 * API_USER
	 * Store the users who want to use the rest api
	 */
	public static final String API_USER_ID					=		"API_USER_ID";
	public static final String USER_ID						=		"USER_ID";
	public static final String PASSWORD						=		"PASSWORD";
	public static final String INVAL_DATE					=		"INVAL_DATE";
	public static final String USER_NAME					=		"USER_NAME";
	public static final String AUTH_MODE					=		"AUTH_MODE";
	public static final String STATUS						=		"STATUS";
	public static final String UNLOCK_TIME					=		"UNLOCK_TIME";
	public static final String IS_NEED_SIGN					=		"IS_NEED_SIGN";
	public static final String MENU_TYPE					=		"MENU_TYPE";
	public static final String LAST_LOGIN					=		"LAST_LOGIN";
	public static final String ERR_COUNT					=		"ERR_COUNT";
	public static final String START_DATE					=		"START_DATE";
	public static final String END_DATE						=		"END_DATE";
	public static final String VALID_TIME					=		"VALID_TIME";
	public static final String IP_ADDRESS					=		"IP_ADDRESS";
	public static final String MAC_CODE						=		"MAC_CODE";
	public static final String EMAIL						=		"EMAIL";
	
	public static final String C_API_USER_ID_ALL			=		"all";
	
//	/*
//	 * API_AC
//	 * Store the user's access.(temporary)
//	 */
//	public static final String API_AC_ID					=		"API_AC_ID";
////	public static final String REST_USER_ID					=		"REST_USER_ID";
////	public static final String USER_ID						=		"USER_ID";
//	public static final String SRC_IP						=		"SRC_IP";
//	public static final String URL_LIST						=		"URL_LIST";
	
	/*
	 * API_APP
	 * Store the APP information which be integrated in the platform
	 */
	public static final String APP_CODE						=		"APP_CODE";
	public static final String APP_NAME						=		"APP_NAME";
	public static final String APP_DESC						=		"APP_DESC";
	public static final String APP_URL						=		"APP_URL";
	public static final String APP_URL_TYPE					=		"APP_URL_TYPE";
	public static final String APP_IP						=		"APP_IP";
	public static final String APP_PORT						=		"APP_PORT";
	public static final String APP_TYPE						=		"APP_TYPE";
	public static final String APP_CALL_TYPE				=		"APP_CALL_TYPE";
	public static final String APP_CALL_HANDLER				=		"APP_CALL_HANDLER";
	public static final String APP_LOGIN_HANDLER			=		"APP_LOGIN_HANDLER";
	
	public static final String LOGIN_TYPE					=		"LOGIN_TYPE";
	public static final String CREATE_TIME					=		"CREATE_TIME";
	public static final String UPDATE_TIME					=		"UPDATE_TIME";
	public static final String UPDATOR						=		"UPDATOR";
	public static final String IN_USE						=		"IN_USE";
	public static final String APP_NEED_HB					=		"APP_NEED_HB";
	public static final String APP_SERVER_LOGIN_INFO		=		"APP_SERVER_LOGIN_INFO";
	public static final String LOGIN_SESSION_MAX_LIFE		=		"LOGIN_SESSION_MAX_LIFE";
	
	public static final String LOGIN_TYPE_NOT_LOGIN			=					"1";
	public static final String LOGIN_TYPE_WHOLE_APP_ONE_TIME			=		"2";
	public static final String LOGIN_TYPE_EVERY_API_USER_ONE_TIME		=		"3";
	public static final String LOGIN_TYPE_EVERY_API_USER_EVERY_TIME		=		"4";
	public static final String LOGIN_TYPE_EVERY_USER_SN_MANUAL_RECOGNIZE_VCODE	=		"5";
	public static final String LOGIN_TYPE_EVERY_USER_SN_NO_VCODE	=		"6";
	
	/**
	 * APP_SERVER_LOGIN_INFO JSON KEY
	 */
	public static final String KEY_SESSION_CREATE_URL			=		"SESSION_CREATE_URL";
	/**
	 * APP_SERVER_LOGIN_INFO JSON KEY
	 */
	public static final String KEY_VALID_CODE_URL				=		"VALID_CODE_URL";
	/**
	 * APP_SERVER_LOGIN_INFO VALIDATE CODE OCR HANDLER
	 */
	public static final String KEY_VALID_CODE_OCR_HANDLER		=		"VALID_CODE_OCR_HANDLER";
	/**
	 * APP_SERVER_LOGIN_INFO
	 */
	public static final String KEY_OTHER_CLIENT_KEY				=		"OTHER_CLIENT_KEY";
	/**
	 * REQUEST_LOGIN_INFO
	 */
	public static final String REQUEST_LOGIN_INFO				=		"_UserLoginInfo";
	/**
	 * APP_SERVER_LOGIN_INFO
	 */
	public static final String KEY_VALID_CODE_KEY				=		"VALID_CODE_KEY";
	
	
	public static final String C_APP_CALL_TYPE_JAVA_WEB 	=		"1";
	public static final String C_APP_CALL_TYPE_PHP_WEB 		=		"2";
	public static final String C_APP_CALL_TYPE_MICROMSG 	=		"3";
	/*
	 * API_SERVICE
	 * Store the SERVICE information which be integrated in the platform
	 */
	public static final String SERV_CODE					=		"SERV_CODE";
//	public static final String APP_CODE						=		"APP_CODE";
	public static final String SERV_NAME					=		"SERV_NAME";
	public static final String SERV_REAL_PATH				=		"SERV_REAL_PATH";
	public static final String SERV_DESC					=		"SERV_DESC";
	public static final String SERV_TYPE					=		"SERV_TYPE";
	public static final String SERV_DATA_TYPE				=		"SERV_DATA_TYPE";
	public static final String SERV_DATA_ENCODE_TYPE		=		"SERV_DATA_ENCODE_TYPE";
	public static final String SERV_DATA_TRAN				=		"SERV_DATA_TRAN";
//	public static final String CREATE_TIME					=		"CREATE_TIME";
//	public static final String UPDATE_TIME					=		"UPDATE_TIME";
//	public static final String UPDATOR						=		"UPDATOR";
//	public static final String IN_USE						=		"IN_USE";
	public static final String DATA_MAX_CACHE_LIFE			=		"DATA_MAX_CACHE_LIFE";
	
	public static final String C_SERV_TYPE_VCODE			=		"3";
	public static final String C_SERV_DATA_TYPE_IMAGE		=		"1";
	
	/*
	 * API_LOG
	 * Store the API Log info
	 */
	public static final String API_LOG_ID					=		"API_LOG_ID";
//	public static final String API_USER_ID					=		"API_USER_ID";
//	public static final String USER_ID						=		"USER_ID";
	public static final String SRC_IP_ADDR					=		"SRC_IP_ADDR";
	public static final String REQUEST_TIME					=		"REQUEST_TIME";
	public static final String USER_AGENT					=		"USER_AGENT";
	public static final String REQUEST_URI					=		"REQUEST_URI";
	public static final String PARAMS						=		"PARAMS";
	public static final String METHOD						=		"METHOD";
	public static final String DST_IP_ADDR					=		"DST_IP_ADDR";
	public static final String DST_API						=		"DST_API";
	public static final String EXCEPTION					=		"EXCEPTION";
	
	/*
	 * API_APP_LOGIN_INFO
	 */
	public static final String APP_LOGIN_INFO				=		"APP_LOGIN_INFO";
	
	/*
	 * APP_LOGIN_INFO JSON KEY
	 */
	public static final String KEY_USER_NAME			=		"USER_NAME";
	/*
	 * APP_LOGIN_INFO JSON KEY
	 */
	public static final String KEY_PASSWORD				=		"PASSWORD";
	
	//########################################################
	//#														 #
	//#						Java Related					 #												
	//#														 #
	//########################################################
		
	/**
	 * token count
	 */
	public static final String TOKEN_COUNT					=		"tokenCount";
	
	/**
	 * token count
	 */
	public static final String MAX_TOKEN_COUNT				=		"maxTokenCount";
	
	/**
	 * 是
	 */
	public static final String TRUE							=		"Y";
	
	/**
	 * 否
	 */
	public static final String FALSE						=		"N";

	/**
	 * 默认登录处理器
	 */
	//public static final String DEFAULT_LOGIN_HANDLER		=		"javaWebDefaultLoginHandler";
	
	/**
	 * 默认登录处理器
	 */
	public static final String VALID_CODE_IMG_BASE_PATH		=		"/imgRecognition/validCode";
	
	//########################################################
	//#														 #
	//#						Redis Related					 #												
	//#														 #
	//########################################################
	
	/**
	 * Redis key prefix : Token Cache
	 */
	public static final String RP_TOKEN					=		"T_";
	
	/**
	 * Redis key prefix : Key Pair Cache
	 */
	public static final String RP_KEY_PAIR					=		"KEY_PAIR";
	
	/**
	 * Redis key prefix : Key User Cache
	 */
	public static final String RP_USER_ID					=		"U_";
	
	/**
	 * Redis key prefix : Key IP Request Count Cache
	 */
	public static final String RP_IP_REQUEST_COUNT			=		"IP_";
	
	/**
	 * Redis key prefix : Cache Max Life
	 */
	public static final String RP_CACHE_MAX_LIFE			=		"CML_";
	
	/**
	 * Redis key prefix : Session Manage Info
	 */
	public static final String RP_SESSION_MANAGE_INFO			=		"SMI_";
	
	/**
	 * Redis key prefix : Session Info
	 */
	public static final String RP_SESSION_INFO			=		"SI_";
	
	/**
	 * Redis key prefix : Now it is just content-type
	 */
	public static final String RP_API_DATA_HEADER_CACHE		=		"ADHC_";
	
	/**
	 * Redis key prefix : encoding type
	 */
	public static final String RP_API_DATA_ENCODING_TYPE	=		"ADET_";
	
	/**
	 * Redis key prefix : Content body
	 */
	public static final String RP_API_DATA_CONTENT_CACHE	=		"ADCC_";
	
	/**
	 * Redis key prefix : API_APP record
	 */
	public static final String RP_API_APP_RECORD_CACHE		=		"AARC_";
	
	/**
	 * Redis key prefix : API_SERVICE record
	 */
	public static final String RP_API_SERVICE_RECORD_CACHE	=		"ASRC_";

	/**
	 * Redis key prefix : App health condition
	 */
	public static final String RP_APP_HEALTH_CONDITION		=		"AHC_";
	
	/**
	 * Redis key : App health condition last refresh time
	 */
	public static final String RK_APP_HEALTH_CONDITION_LAST_REFRESH_TIME	=		"_AHCLRT";

	/**
	 * Redis key prefix : API_LOG record
	 */
	public static final String RP_API_LOG_RECORD_CACHE		=		"ALRC_";
	
	/**
	 * access app and service
	 */
	public static final String ACCESS_APP_SERV				=		"ACCESS_APP_SERV";
	/**
	 * Redis key prefix : vcode cookie 
	 */
	public static final String RP_VCODE_COOKIE_STR			=		"RVCS_";


}
