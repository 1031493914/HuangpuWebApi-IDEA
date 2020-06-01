package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysBuildingInfoRowMapper implements RowMapper<SysBuildingInfo>{



	@Override
	public SysBuildingInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysBuildingInfo  lj= new SysBuildingInfo();
		lj.setId(rs.getInt("id"));
		lj.setName(rs.getString("name"));
		lj.setAmount(rs.getDouble("area"));
		lj.setCycle(rs.getString("cycle"));
		lj.setArea(rs.getDouble("area"));
		lj.setDesign_unit(rs.getString("design_unit"));
		lj.setContractor(rs.getString("contractor"));
		lj.setConstruction_unit(rs.getString("construction_unit"));
		lj.setSupervision_unit(rs.getString("supervision_unit"));
		lj.setType(rs.getString("type"));
		lj.setPrincipal(rs.getString("principal"));
		lj.setVideos(rs.getInt("videos"));
		lj.setAddress(rs.getString("adderss"));
		lj.setStreet(rs.getString("street"));
		lj.setLatitude(rs.getString("Latitude"));
		lj.setLongitude(rs.getString("Longitude"));
		lj.setCreatetime(rs.getTimestamp("createtime"));
		lj.setUsepeople(rs.getInt("usepeople"));
		lj.setConnectmobile(rs.getString("connectmobile"));
		return lj;
	}

}
