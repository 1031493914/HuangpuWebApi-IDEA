package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpEmergencyZhaMenRowMapper implements RowMapper<SysHpEmergencyZhaMen>{


//	private String name;
//	private String control_unit;
//	private String control_unit_address;
//	private String phone;
//	private String zhibanphone;

	@Override
	public SysHpEmergencyZhaMen mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpEmergencyZhaMen  lj= new SysHpEmergencyZhaMen();
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setId(rs.getInt("id"));
		lj.setName(rs.getString("name"));
		lj.setControl_unit(rs.getString("control_unit"));
		lj.setControl_unit_address(rs.getString("control_unit_address"));
		lj.setPhone(rs.getString("phone"));
		lj.setZhibanphone(rs.getString("zhibanphone"));
		return lj;
	}

}
