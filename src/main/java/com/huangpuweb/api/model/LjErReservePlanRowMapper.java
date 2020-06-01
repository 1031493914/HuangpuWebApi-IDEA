package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErReservePlanRowMapper implements RowMapper<LjErReservePlan>{


	@Override
	public LjErReservePlan mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErReservePlan  lj= new LjErReservePlan();
		lj.setTitle(rs.getString("Title"));
		lj.setContent(rs.getString("Content"));
		lj.setGuid(rs.getString("Guid"));
		lj.setKind(rs.getString("Kind"));
		lj.setOpTime(rs.getTimestamp("OpTime"));
		lj.setOrgan(rs.getString("Organ"));
		lj.setStatus(rs.getInt("Status"));
		lj.setCreateDay(rs.getTimestamp("CreateDay"));
		lj.setLevel(rs.getInt("Level"));
		return lj;
	}

}
