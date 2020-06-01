package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 应急队伍
 */
@Data
public class HpDianZhaoDianPaiInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	//百度经度
	private String BaiduLongitude;
	//百度纬度
	private String BaiduLatitude;
	private String street;
	private double length;
	private double height;
	//事件处理部门
	private String connectmobile;
	private String connectname;
	private String imageurl;
}
