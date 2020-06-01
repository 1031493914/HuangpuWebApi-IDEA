package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 预案库 lj_er_reserve_plan
 */
@Data
public class LjErReservePlan implements Serializable{


	private static final long serialVersionUID = 1L;
	//GUID
	private String Guid;
	//级别
	private Integer Level;
	//类型
	private String Kind;
	//部门
	private String Organ;
	//标题
	private String Title;
	//内容
	private String Content;
	//状态
	private Integer Status;
	//操作时间
	private Date OpTime;
	//制定日期
	private Date CreateDay;


}
