package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyTeamRowMapper implements RowMapper<SysHpEmergencyTeam>{


//	private int id;
//	private int teamtype;
//
//	private String unitName;
//	private String createTime;
//	private String teamState;
//	private Integer peoperCount;
//	private String contact;
//	private String contactPhone;
//	private String teamName;
//	private String taskType;
//	private String BaiduLongitude;
//	private String BaiduLatitude;

	@Override
	public SysHpEmergencyTeam mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyTeam  lj= new SysHpEmergencyTeam();
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setId(rs.getInt("id"));
		lj.setUnitName(rs.getString("unitName"));
		lj.setTeamState(rs.getString("teamState"));
		lj.setPeoperCount(rs.getInt("peoperCount"));
		lj.setContact(rs.getString("contact"));
		lj.setContactPhone(rs.getString("contactPhone"));
		lj.setTeamName(rs.getString("teamName"));
		lj.setTaskType(rs.getString("taskType"));

		return lj;
	}

}
