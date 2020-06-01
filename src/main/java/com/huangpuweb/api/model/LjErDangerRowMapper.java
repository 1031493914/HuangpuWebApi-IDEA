package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErDangerRowMapper implements RowMapper<LjErDanger>{



	@Override
	public LjErDanger mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErDanger  lj= new LjErDanger();
		lj.setRiskLevel(rs.getInt("RiskLevel"));
		lj.setFactor(rs.getString("Factor"));
		lj.setLatitude(rs.getString("Latitude"));
		lj.setLongitude(rs.getString("Longitude"));
		lj.setStreet(rs.getString("Street"));
		lj.setAddress(rs.getString("Address"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setHazardName(rs.getString("HazardName"));
		return lj;
	}

}
