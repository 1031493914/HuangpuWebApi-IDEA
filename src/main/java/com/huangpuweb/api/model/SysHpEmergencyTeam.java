package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysHpEmergencyTeam implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int teamtype;

	private String unitName;
	private String createTime;
	private String teamState;
	private Integer peoperCount;
	private String contact;
	private String contactPhone;
	private String teamName;
	private String taskType;
	private String BaiduLongitude;
	private String BaiduLatitude;



}
