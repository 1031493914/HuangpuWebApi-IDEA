package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HpDianZhaoDianPaiInfoRowMapper implements RowMapper<HpDianZhaoDianPaiInfo>{



	@Override
	public HpDianZhaoDianPaiInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		HpDianZhaoDianPaiInfo  lj= new HpDianZhaoDianPaiInfo();
		lj.setId(rs.getInt("id"));
		lj.setName(rs.getString("name"));
		lj.setAddress(rs.getString("address"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setStreet(rs.getString("street"));
//		lj.setLength(rs.getDouble("length"));
//		lj.setHeight(rs.getDouble("height"));
		lj.setConnectmobile(rs.getString("connectmobile"));
		lj.setConnectname(rs.getString("connectname"));
		lj.setImageurl(rs.getString("imageurl"));
		return lj;
	}

}
