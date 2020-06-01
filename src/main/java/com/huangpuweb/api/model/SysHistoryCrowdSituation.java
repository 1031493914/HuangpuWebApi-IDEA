package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;


//sys_history_crowdSituation
@Data
public class SysHistoryCrowdSituation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    //景区名称
    private String spotName;


    //景区编码
    private String spotCode;

    //峰值时间
    private String maxTime;


    //数据记录时间
    private String recordTime;

    //当日峰值
    private Integer maxNumber;

    //当日总人数
    private Integer totalNumber;

    //预警次数
    private Integer warningNumber;


    //数据来源
    private String dataSources;

}
