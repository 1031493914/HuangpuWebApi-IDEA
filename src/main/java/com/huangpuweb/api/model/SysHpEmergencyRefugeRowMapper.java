package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyRefugeRowMapper implements RowMapper<SysHpEmergencyRefuge>{


//	private int id;
//private String refugeName;
//	private String address;
//	private String baiduLatitude;
//	private String baiduLongitude;
//	private String connectname;
//	private String connectmobile;


	@Override
	public SysHpEmergencyRefuge mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyRefuge  lj= new SysHpEmergencyRefuge();
		lj.setBaiduLongitude(rs.getString("baiduLongitude"));
		lj.setBaiduLatitude(rs.getString("baiduLatitude"));
		lj.setId(rs.getInt("id"));
		lj.setRefugeName(rs.getString("refugeName"));
		lj.setAddress(rs.getString("address"));
		lj.setConnectname(rs.getString("connectname"));
		lj.setConnectmobile(rs.getString("connectmobile"));

		return lj;
	}

}
