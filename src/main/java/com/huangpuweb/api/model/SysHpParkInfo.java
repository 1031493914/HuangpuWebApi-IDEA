package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 停车场综合信息实体类 hp_park_info
 */
@Data
public class SysHpParkInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //uuid
    private String id;
    //停车场编码
    private String parkId;
    //停车位总数
    private Integer total;
    //剩余车位数
    private Integer total_left;
    //已停车辆数
    private Integer parkingNumber;
    //记录时间
    private String recordTime;
    //同步时间
    private String updateTime;

}
