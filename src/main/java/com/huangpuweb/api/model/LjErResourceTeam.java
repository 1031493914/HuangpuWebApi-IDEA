package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 应急队伍
 */
@Data
public class LjErResourceTeam implements Serializable{

	private static final long serialVersionUID = 1L;
	//名称
	private String Name;
	//联系人
	private String Contacts;
	//部门
	private String Organ;
	//人数
	private int Count;
	//地点
	private String Address;
	//联系人电话
	private String ContactsTel;
	//承担任务类型
	private String TaskType;
	//成立时间
	private Date FoundDay;




}
