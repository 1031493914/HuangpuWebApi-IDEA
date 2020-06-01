package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 应急队伍
 */
@Data
public class LjErResourceGoods implements Serializable{

	private static final long serialVersionUID = 1L;
	//数量说明
	private String CountEx;
	//GPS经度
	private String Longitude;
	//GPS纬度
	private String Latitude;
	//百度经度
	private String BaiduLongitude;
	//百度纬度
	private String BaiduLatitude;
	//联系人
	private String Contacts;
	//类型
	private String Type;
	//物资名称
	private String Name;
	//联系人电话
	private String ContactsTel;
	//部门
	private String Organ;
	//储备地点
	private String StoreAddress;



}
