package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyEventRowMapper implements RowMapper<SysHpEmergencyEvent>{


//
//	private String eventName;
//	private String eventType;
//	private String eventTypeNext;
//	//百度经度
//	private String BaiduLongitude;
//	//百度纬度
//	private String BaiduLatitude;
//	private String address;
//	private Date startTime;
//	private Date endTime;
//	//事件处理部门
//	private String eventDepartment;
//	private String eventcontent;
//	private String connectmMobile;
//	private String connectName;

	@Override
	public SysHpEmergencyEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyEvent  lj= new SysHpEmergencyEvent();
		lj.setAddress(rs.getString("Address"));
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setEventName(rs.getString("eventName"));
		lj.setEventType(rs.getString("eventType"));
		lj.setStartTime(rs.getTimestamp("startTime"));
		lj.setEndTime(rs.getTimestamp("endTime"));
		lj.setEventDepartment(rs.getString("eventDepartment"));
		lj.setEventcontent(rs.getString("eventcontent"));
		lj.setConnectMobile(rs.getString("connectMobile"));
		lj.setConnectName(rs.getString("connectName"));
		return lj;
	}

}
