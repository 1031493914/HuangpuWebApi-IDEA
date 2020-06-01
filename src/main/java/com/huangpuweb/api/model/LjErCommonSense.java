package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 知识库 lj_er_common_sense
 */
@Data
public class LjErCommonSense implements Serializable{


	private static final long serialVersionUID = 1L;
	//GUID
	private String Guid;
	//标题
	private String Title;
	//内容
	private String Content;
	//操作时间
	private Date OpTime;


}
