package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysHpEmergencyWaterengine implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String addresss;

	private String baiduLongitude;
	private String baiduLatitude;

	private Integer enginenum;
	private String waternum;
	private String powernum;
	private Integer type;





}
