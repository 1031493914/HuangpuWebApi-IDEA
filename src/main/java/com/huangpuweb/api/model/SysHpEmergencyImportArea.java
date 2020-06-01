package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysHpEmergencyImportArea implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String areaName;
	private String connectName;
	private String connectMobile;
	private String zhibanMobile;
	private String ownerunit;
	private String BaiduLongitude;
	private String BaiduLatitude;



}
