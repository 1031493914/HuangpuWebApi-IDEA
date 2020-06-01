package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHpParkRowMapper implements RowMapper<SysHpPark>{



	@Override
	public SysHpPark mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHpPark  lj= new SysHpPark();
		lj.setParkId(rs.getString("parkId"));
		lj.setSpotCode(rs.getString("spotCode"));
		lj.setParkName(rs.getString("parkName"));
		lj.setParkAddress(rs.getString("parkAddress"));
		return lj;
	}

}
