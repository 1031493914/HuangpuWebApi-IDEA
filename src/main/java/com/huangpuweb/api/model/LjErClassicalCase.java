package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 案例库 lj_er_classical_case
 */
@Data
public class LjErClassicalCase implements Serializable{


	private static final long serialVersionUID = 1L;
	//GUID
	private String Guid;
	//类型
	private Integer Kind;
	//部门
	private String Organ;
	//标题
	private String Title;
	//内容
	private String Content;
	//状态
	private Integer Status;
	//
	private Date OpTime;


}
