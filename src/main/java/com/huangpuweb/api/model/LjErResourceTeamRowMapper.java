package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErResourceTeamRowMapper implements RowMapper<LjErResourceTeam>{

	@Override
	public LjErResourceTeam mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErResourceTeam  lj= new LjErResourceTeam();
		lj.setName(rs.getString("Name"));
		lj.setContacts(rs.getString("Contacts"));
		lj.setOrgan(rs.getString("Organ"));
		lj.setCount(rs.getInt("Count"));
		lj.setAddress(rs.getString("Address"));
		lj.setContactsTel(rs.getString("ContactsTel"));
		lj.setTaskType(rs.getString("TaskType"));
		lj.setFoundDay(rs.getTimestamp("FoundDay"));
		return lj;
	}

}
