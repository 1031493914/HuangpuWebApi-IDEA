package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErResourceExpertsRowMapper implements RowMapper<LjErResourceExperts>{


	@Override
	public LjErResourceExperts mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErResourceExperts  lj= new LjErResourceExperts();
		lj.setName(rs.getString("Name"));

		lj.setOrgan(rs.getString("Organ"));
		lj.setSuddenAffairType(rs.getString("SuddenAffairType"));
		lj.setFoundDay(rs.getTimestamp("FoundDay"));
		lj.setNameList(rs.getString("NameList"));
		return lj;
	}

}
