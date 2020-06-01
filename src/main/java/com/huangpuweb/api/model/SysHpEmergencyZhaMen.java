package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 应急队伍
 */
@Data
public class SysHpEmergencyZhaMen implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String control_unit;
	private String control_unit_address;
	private String phone;
	private String zhibanphone;
	private String BaiduLongitude;
	private String BaiduLatitude;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getControl_unit() {
		return control_unit;
	}

	public void setControl_unit(String control_unit) {
		this.control_unit = control_unit;
	}

	public String getControl_unit_address() {
		return control_unit_address;
	}

	public void setControl_unit_address(String control_unit_address) {
		this.control_unit_address = control_unit_address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZhibanphone() {
		return zhibanphone;
	}

	public void setZhibanphone(String zhibanphone) {
		this.zhibanphone = zhibanphone;
	}

	public String getBaiduLongitude() {
		return BaiduLongitude;
	}

	public void setBaiduLongitude(String baiduLongitude) {
		BaiduLongitude = baiduLongitude;
	}

	public String getBaiduLatitude() {
		return BaiduLatitude;
	}

	public void setBaiduLatitude(String baiduLatitude) {
		BaiduLatitude = baiduLatitude;
	}
}
