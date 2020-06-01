package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZyHpEmergencyEventRowMapper implements RowMapper<SysHpEmergencyEvent>{


	@Override
	public SysHpEmergencyEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyEvent  lj= new SysHpEmergencyEvent();
		lj.setAddress(rs.getString("Address"));
//		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
//		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setEventName(rs.getString("Question"));
		lj.setEventType(rs.getString("WorkLoglargeType"));
		lj.setStartTime(rs.getTimestamp("beginTime"));
		lj.setEndTime(rs.getTimestamp("endTime"));
		lj.setEventDepartment(rs.getString("Unit"));
		lj.setEventcontent(rs.getString("Question"));
		lj.setConnectMobile(rs.getString("Tel"));
		lj.setConnectName(rs.getString("DutyName"));
		lj.setGuid(rs.getString("Guid"));
		return lj;
	}

}
