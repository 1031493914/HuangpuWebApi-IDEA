package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrowdSysSettingRowMapper implements RowMapper<CrowdSysSetting>{



	@Override
	public CrowdSysSetting mapRow(ResultSet rs, int rowNum) throws SQLException {
		CrowdSysSetting  lj= new CrowdSysSetting();
		lj.setId(rs.getInt("id"));
		lj.setSpotcode(rs.getString("spotcode"));
		lj.setYearOnYear(rs.getInt("yearOnYear"));
		lj.setMonthOnMonth(rs.getInt("monthOnMonth"));
		return lj;
	}

}
