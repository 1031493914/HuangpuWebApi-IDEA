package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyDangerRowMapper implements RowMapper<SysHpEmergencyDanger>{

//	private int id;
//	private String address;
//	private String riskFactor;
//	private String baiduLongitude;
//	private String baiduLatitude;
//	private int type;
//	private String owerunit;
//	private String connectname;
//	private String helpunit;
//	private String connectmobile;
//	private String helpconnectname;
//	private String helpconnectmobile;

	@Override
	public SysHpEmergencyDanger mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyDanger  lj= new SysHpEmergencyDanger();
		lj.setId(rs.getInt("id"));
		lj.setAddress(rs.getString("address"));
		lj.setBaiduLongitude(rs.getString("baiduLongitude"));
		lj.setBaiduLatitude(rs.getString("baiduLatitude"));
		lj.setRiskFactor(rs.getString("riskFactor"));
		lj.setType(rs.getInt("type"));
		lj.setOwerunit(rs.getString("owerunit"));
		lj.setConnectname(rs.getString("connectname"));
		lj.setConnectmobile(rs.getString("connectmobile"));
		lj.setHelpunit(rs.getString("helpunit"));
		lj.setHelpconnectname(rs.getString("helpconnectname"));
		lj.setHelpconnectmobile(rs.getString("helpconnectmobile"));
		return lj;
	}

}
