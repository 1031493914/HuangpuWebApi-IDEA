package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErCommonSenseRowMapper implements RowMapper<LjErCommonSense>{
	@Override
	public LjErCommonSense mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErCommonSense  lj= new LjErCommonSense();
		lj.setTitle(rs.getString("Title"));
		lj.setContent(rs.getString("Content"));
		lj.setGuid(rs.getString("Guid"));
		lj.setOpTime(rs.getTimestamp("OpTime"));
		return lj;
	}
}
