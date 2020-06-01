package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HpSubWayExitInfoRowMapper implements RowMapper<HpSubwayExitInfo>{



	@Override
	public HpSubwayExitInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		HpSubwayExitInfo  lj= new HpSubwayExitInfo();
		lj.setId(rs.getString("id"));
		lj.setSubwayId(rs.getString("subwayId"));
		lj.setSpotCode(rs.getString("spotCode"));
		lj.setInTotalNum(rs.getInt("inTotalNum"));
		lj.setOutTotalNum(rs.getInt("outTotalNum"));
		lj.setZdmc(rs.getString("zdmc"));
		lj.setCreate_time(rs.getString("create_time"));
		return lj;
	}

}
