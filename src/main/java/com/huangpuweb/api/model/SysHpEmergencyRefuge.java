package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysHpEmergencyRefuge implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String refugeName;
	private String address;
	private String baiduLatitude;
	private String baiduLongitude;
	private String connectname;
	private String connectmobile;

}
