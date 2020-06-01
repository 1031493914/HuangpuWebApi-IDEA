package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysHpEmergencyFireControl implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private String connectmobile;
	private String connectname;
	private Integer peoplenum;
	private String area;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConnectmobile() {
		return connectmobile;
	}

	public void setConnectmobile(String connectmobile) {
		this.connectmobile = connectmobile;
	}

	public String getConnectname() {
		return connectname;
	}

	public void setConnectname(String connectname) {
		this.connectname = connectname;
	}

	public Integer getPeoplenum() {
		return peoplenum;
	}

	public void setPeoplenum(Integer peoplenum) {
		this.peoplenum = peoplenum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
