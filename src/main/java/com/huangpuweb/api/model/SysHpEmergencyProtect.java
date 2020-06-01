package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysHpEmergencyProtect implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String connectname;
	private String connectmobile;
	private String baiduLongitude;
	private String baiduLatitude;
	private int type;





}
