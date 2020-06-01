package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 应急队伍
 */
@Data
public class LjErDanger implements Serializable{

	private static final long serialVersionUID = 1L;
	//风险等级
	private int RiskLevel;
	//危险源、危险区域要素
	private String Factor;
	//状态
	private int StatusEx;
	//应急准备落实情况
	private String ImplementInfo;
	//GPS纬度
	private String Latitude;
	//GPS经度
	private String Longitude;
	//街道
	private String Street;
	//地址
	private String Address;
	//百度经度
	private String BaiduLongitude;
	//百度纬度
	private String BaiduLatitude;
	//危险源、危险区域名称
	private String HazardName;
}
