package com.huangpuweb.api.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LjErResourceGoodsRowMapper implements RowMapper<LjErResourceGoods>{


//	//数量说明
//	private String CountEx;
//	//GPS经度
//	private String Longitude;
//	//GPS纬度
//	private String Latitude;
//	//百度经度
//	private String BaiduLongitude;
//	//百度纬度
//	private String BaiduLatitude;
//	//联系人
//	private String Contacts;
//	//类型
//	private String Type;
//	//物资名称
//	private String Name;
//	//联系人电话
//	private String ContactsTel;
//	//部门
//	private String Organ;
//	//储备地点
//	private String StoreAddress;
	@Override
	public LjErResourceGoods mapRow(ResultSet rs, int rowNum) throws SQLException {
		LjErResourceGoods  lj= new LjErResourceGoods();
		lj.setName(rs.getString("Name"));
		lj.setContacts(rs.getString("Contacts"));
		lj.setOrgan(rs.getString("Organ"));
		lj.setContactsTel(rs.getString("ContactsTel"));
		lj.setCountEx(rs.getString("CountEx"));
		lj.setLatitude(rs.getString("Latitude"));
		lj.setLongitude(rs.getString("Longitude"));
		lj.setBaiduLongitude(rs.getString("BaiduLongitude"));
		lj.setBaiduLatitude(rs.getString("BaiduLatitude"));
		lj.setType(rs.getString("Type"));
		lj.setStoreAddress(rs.getString("StoreAddress"));

		return lj;
	}

}
