package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 应急队伍
 */
@Data
public class SysHpEmergencyEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	private String eventName;
	private String eventType;
	private String eventTypeNext;
	//百度经度
	private String BaiduLongitude;
	//百度纬度
	private String BaiduLatitude;
	private String address;
	private Date startTime;
	private Date endTime;
	//事件处理部门
	private String eventDepartment;
	private String eventcontent;
	private String connectMobile;
	private String connectName;
	private String Guid;
}
