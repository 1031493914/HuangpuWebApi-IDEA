package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 应急队伍
 */
@Data
public class LjErResourceExperts implements Serializable{

	private static final long serialVersionUID = 1L;
	//部门
	private String Organ;
	//承担突发事件类型
	private String SuddenAffairType;
	//成立时间
	private Date FoundDay;
	//专家组名称
	private String Name;
	//专家组名单
	private String NameList;



}
