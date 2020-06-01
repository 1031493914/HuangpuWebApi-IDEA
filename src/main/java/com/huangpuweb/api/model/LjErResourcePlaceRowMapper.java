package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErResourcePlaceRowMapper implements RowMapper<LjErResourcePlace>{


	@Override
	public LjErResourcePlace mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErResourcePlace  lj= new LjErResourcePlace();
		lj.setName(rs.getString("Name"));
		lj.setAddress(rs.getString("Address"));
		lj.setLatitude(rs.getString("Latitude"));
		lj.setLongitude(rs.getString("Longitude"));
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		return lj;
	}

}
