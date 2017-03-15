package com.cidic.sdx.util;

public class MatchCodeUtil {
	
	//0:初始化  1：图片缺失 2：链接缺失 3：图片链接都缺失  
	public static final int MATCH_DATA_STATUS_INIT_VALUE = 0;
	public static final int MATCH_DATA_STATUS_IMAGELOST_VALUE = 1;
	public static final int MATCH_DATA_STATUS_URLLOST_VALUE = 2;
	public static final int MATCH_DATA_STATUS_ALLLOST_VALUE = 3;
	
	//0:未分享 1：已分享
	public static final int MATCH_NOT_SHARE_STATUS_SIGN = 0;
	public static final int MATCH_SHARE_STATUS_SIGN = 1;
}
