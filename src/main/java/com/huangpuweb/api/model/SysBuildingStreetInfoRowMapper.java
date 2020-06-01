package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysBuildingStreetInfoRowMapper implements RowMapper<SysBuildingStreetInfo>{



	@Override
	public SysBuildingStreetInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysBuildingStreetInfo  lj= new SysBuildingStreetInfo();
		lj.setStreet(rs.getString("street"));
		lj.setBuildingNum(rs.getInt("buildingnum"));
		return lj;
	}

}
