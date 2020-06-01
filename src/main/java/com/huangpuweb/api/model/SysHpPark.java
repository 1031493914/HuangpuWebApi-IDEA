package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 停车场  hp_park
 */

@Data
public class SysHpPark implements Serializable {
    private static final long serialVersionUID = 1L;

    //"停车场编码"
    private String parkId;
    //景区编码
    private String spotCode;
    //停车场名称
    private String parkName;
    //  停车场地址
    private String parkAddress;

}
