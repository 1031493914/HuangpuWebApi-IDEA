package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErClassicalCaseRowMapper implements RowMapper<LjErClassicalCase>{



	@Override
	public LjErClassicalCase mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErClassicalCase  lj= new LjErClassicalCase();
		lj.setTitle(rs.getString("Title"));
		lj.setContent(rs.getString("Content"));
		lj.setGuid(rs.getString("Guid"));
		lj.setKind(rs.getInt("Kind"));
		lj.setOpTime(rs.getTimestamp("OpTime"));
		lj.setOrgan(rs.getString("Organ"));
		lj.setStatus(rs.getInt("Status"));
		return lj;
	}

}
