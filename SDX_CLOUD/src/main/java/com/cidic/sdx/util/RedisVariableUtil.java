package com.cidic.sdx.util;

/**
 * Redis数据存储前缀
 * @author dev
 *
 */
public class RedisVariableUtil {

	public final static String DIVISION_CHAR = ":";
	
	public final static String BRAND_PREFIX = "brand";  //品牌
	public final static String CATEGORY_PREFIX = "category"; //品类
	public final static String COLOR_PREFIX = "color"; //颜色
	public final static String SIZE_PREFIX = "size"; //尺寸
	public final static String DATETIME_PREFIX = "date_time";//时间标签
	
	public final static String HP_RECORD_PREFIX = "hp"; //货品管理记录前缀
	
	public final static String BRAND_TAG_PREFIX = "tag_brand";  //品牌标签前缀
	public final static String CATEGORY_TAG_PREFIX = "tag_category"; //品类标签前缀
	public final static String COLOR_TAG_PREFIX = "tag_color"; //颜色标签前缀
	public final static String SIZE_TAG_PREFIX = "tag_size"; //尺寸标签前缀
	public final static String DATETIME_TAG_PREFIX = "tag_datetime"; //时间标签前缀
	
	public final static String USER_PRIFIX = "user";
	public final static String ROLE_PRIFIX = "role";
	public final static String PERMISSION_PRIFIX = "permission";
	
	public final static String LOST_IMAGE_LIST = "lostImageList";
	public final static String LOST_URL_LIST = "lostURLList";
	public final static String LOST_ALL_LIST = "lostAllList";
	
	public final static String PRODUCT_Unshelve_SET = "ProductUnshelveSet";
	
	public final static String LIST_INNER_CLOTH = "innnerClothList";
	public final static String LIST_OUTTER_CLOTH = "outterClothList";
	public final static String LIST_TROUSER_CLOTH = "trouserList";
	
	
	public final static int DATA_STATUS_INTEGRITY = 0;
	public final static int DATA_STATUS_URL_LOST = 2;
	public final static int DATA_STATUS_IMAGE_LOST = 1;
	public final static int DATA_STATUS_ALL_LOST = 3;
}
