package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class SysBuildingInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	//项目名称
	private String name;
	//项目金额
	private Double amount;
	//建设周期
	private String cycle;
	//建设面积
	private Double area;
	//设计单位
	private String design_unit;
	//承建单位
	private String contractor;
	//建设单位
	private String construction_unit;
	//监理单位
	private String supervision_unit;
	//项目类型
	private String type;
	//项目功能
	private String features;
	//负责人
	private String principal;
	//所属街道
	private String street;
	//更新时间
	private Date updateTime;
	//建设地点
	private String address;
	//经度
	private String Longitude;
	//纬度
	private String Latitude;
	//创建时间
	private Date createtime;
	//视频数量
	private Integer videos;
	//工地数量
	private Integer buildingNum;
	//用工数量
	private Integer usepeople;
	//联系电话
	private String connectmobile;


}
