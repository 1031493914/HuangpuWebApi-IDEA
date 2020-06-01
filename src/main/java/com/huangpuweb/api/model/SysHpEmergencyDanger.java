package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysHpEmergencyDanger implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String address;
	private String riskFactor;
	private String baiduLongitude;
	private String baiduLatitude;
	private int type;
	private String owerunit;
	private String connectname;
	private String helpunit;
	private String connectmobile;
	private String helpconnectname;
	private String helpconnectmobile;




}
