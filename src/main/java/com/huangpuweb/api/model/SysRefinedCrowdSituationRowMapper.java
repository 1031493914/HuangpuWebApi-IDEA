package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysRefinedCrowdSituationRowMapper implements RowMapper<SysRefinedCrowdSituation>{



	@Override
	public SysRefinedCrowdSituation mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysRefinedCrowdSituation  lj= new SysRefinedCrowdSituation();
		lj.setId(rs.getString("id"));
		lj.setUuid(rs.getInt("uuid"));
		lj.setSpotName(rs.getString("spotName"));
		lj.setSpotCode(rs.getString("spotCode"));
		lj.setRealtimeNumber(rs.getInt("realtimeNumber"));
		lj.setTotalNumber(rs.getInt("totalNumber"));
		lj.setCreateTime(rs.getString("createTime"));
		lj.setUpdateTime(rs.getString("updateTime"));
		lj.setDataSources(rs.getString("dataSources"));
		lj.setRemarks(rs.getString("remarks"));
		return lj;
	}

}
