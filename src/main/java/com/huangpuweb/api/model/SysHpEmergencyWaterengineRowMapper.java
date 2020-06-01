package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyWaterengineRowMapper implements RowMapper<SysHpEmergencyWaterengine>{


//	private int id;
//	private String name;
//	private String addresss;
//
//	private String baiduLongitude;
//	private String baiduLatitude;
//
//	private Integer enginenum;
//	private String waternum;
//	private String powernum;
//	private Integer type;

	@Override
	public SysHpEmergencyWaterengine mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyWaterengine  lj= new SysHpEmergencyWaterengine();
		lj.setBaiduLongitude(rs.getString("baiduLongitude"));
		lj.setBaiduLatitude(rs.getString("baiduLatitude"));
		lj.setId(rs.getInt("id"));
		lj.setName(rs.getString("name"));
		lj.setAddresss(rs.getString("address"));
		lj.setEnginenum(rs.getInt("enginenum"));
		lj.setWaternum(rs.getString("waternum"));
		lj.setPowernum(rs.getString("powernum"));

		return lj;
	}

}
