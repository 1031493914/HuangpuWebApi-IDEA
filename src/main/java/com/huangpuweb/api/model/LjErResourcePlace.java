package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 应急队伍
 */
@Data
public class LjErResourcePlace implements Serializable{



//	场所地址	Address
//	GPS经度	Longitude
//	GUID	Guid
//	百度经度	BaiduLongitude
//	操作时间	OpTime
//	场所名称	Name
//	jhpt_delete	jhpt_delete
//	类别	Type
//	街道	Street
//	GPS纬度	Latitude
//	操作人	OpUser
//	备注	Comments
//	jhpt_update_time	jhpt_update_time
//	百度纬度	BaiduLatitude

	private static final long serialVersionUID = 1L;

	private String Address;
	//GPS纬度
	private String Latitude;
	//GPS经度
	private String Longitude;
	//街道
	private String Street;
	//地址
	private String Type;
	//百度经度
	private String BaiduLongitude;
	//百度纬度
	private String BaiduLatitude;

	private String Name;


}
