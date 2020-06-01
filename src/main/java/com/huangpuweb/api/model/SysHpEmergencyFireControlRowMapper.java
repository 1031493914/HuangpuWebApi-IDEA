package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyFireControlRowMapper implements RowMapper<SysHpEmergencyFireControl>{



	@Override
	public SysHpEmergencyFireControl mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyFireControl  lj= new SysHpEmergencyFireControl();

		lj.setId(rs.getInt("id"));
		lj.setName(rs.getString("name"));
		lj.setAddress(rs.getString("address"));
		lj.setConnectmobile(rs.getString("connectmobile"));
		lj.setConnectname(rs.getString("connectname"));
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setPeoplenum(rs.getInt("peoplenum"));
		lj.setArea(rs.getString("area"));
		return lj;
	}

}
