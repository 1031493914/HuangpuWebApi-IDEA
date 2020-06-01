package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class CrowdSysSetting implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String spotcode;
	private Integer yearOnYear;
	private Integer monthOnMonth;

}
