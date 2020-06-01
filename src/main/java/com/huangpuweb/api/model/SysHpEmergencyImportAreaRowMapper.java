package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyImportAreaRowMapper implements RowMapper<SysHpEmergencyImportArea>{


//	private String areaName;
//	private String connectName;
//	private String connectMobile;
//	private String zhibanMobile;
//	private String ownerunit;
//	private String BaiduLongitude;
//	private String BaiduLatitude;

	@Override
	public SysHpEmergencyImportArea mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyImportArea  lj= new SysHpEmergencyImportArea();
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setId(rs.getInt("id"));
		lj.setAreaName(rs.getString("areaName"));
		lj.setConnectName(rs.getString("connectName"));
		lj.setConnectMobile(rs.getString("connectMobile"));
		lj.setZhibanMobile(rs.getString("zhibanMobile"));
		lj.setOwnerunit(rs.getString("ownerunit"));

		return lj;
	}

}
