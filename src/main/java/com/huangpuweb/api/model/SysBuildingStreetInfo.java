package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class SysBuildingStreetInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	//所属街道
	private String street;
	//工地数量
	private Integer buildingNum;

}
