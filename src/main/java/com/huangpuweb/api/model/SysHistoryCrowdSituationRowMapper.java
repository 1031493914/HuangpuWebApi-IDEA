package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysHistoryCrowdSituationRowMapper implements RowMapper<SysHistoryCrowdSituation>{



	@Override
	public SysHistoryCrowdSituation mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysHistoryCrowdSituation  lj= new SysHistoryCrowdSituation();
		lj.setId(rs.getString("id"));
		lj.setSpotName(rs.getString("spotName"));
		lj.setSpotCode(rs.getString("spotCode"));
		lj.setMaxTime(rs.getString("maxTime"));
		lj.setRecordTime(rs.getString("recordTime"));
		lj.setMaxNumber(rs.getInt("maxNumber"));
		lj.setWarningNumber(rs.getInt("warningNumber"));
		lj.setTotalNumber(rs.getInt("totalNumber"));
		lj.setDataSources(rs.getString("dataSources"));
		return lj;
	}

}
