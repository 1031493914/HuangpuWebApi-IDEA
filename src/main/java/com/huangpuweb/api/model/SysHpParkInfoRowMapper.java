package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpParkInfoRowMapper implements RowMapper<SysHpParkInfo>{



	@Override
	public SysHpParkInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpParkInfo  lj= new SysHpParkInfo();
		lj.setId(rs.getString("id"));
		lj.setTotal(rs.getInt("total"));
		lj.setTotal_left(rs.getInt("total_left"));
		lj.setParkingNumber(rs.getInt("parkingNumber"));
		lj.setRecordTime(rs.getString("recordTime"));
		lj.setUpdateTime(rs.getString("updateTime"));
		lj.setParkId(rs.getString("parkId"));

		return lj;
	}

}
