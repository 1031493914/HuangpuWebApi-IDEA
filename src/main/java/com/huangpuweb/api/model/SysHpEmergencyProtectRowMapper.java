package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyProtectRowMapper implements RowMapper<SysHpEmergencyProtect>{


//	private int id;
//	private String name;
//	private String connectname;
//	private String connectmobile;
//	private String baiduLongitude;
//	private String baiduLatitude;
//	private int type;


	@Override
	public SysHpEmergencyProtect mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyProtect  lj= new SysHpEmergencyProtect();
		lj.setBaiduLongitude(rs.getString("baiduLongitude"));
		lj.setBaiduLatitude(rs.getString("baiduLatitude"));
		lj.setId(rs.getInt("id"));
		lj.setName(rs.getString("name"));
		lj.setConnectname(rs.getString("connectname"));
		lj.setConnectmobile(rs.getString("connectmobile"));

		return lj;
	}

}
