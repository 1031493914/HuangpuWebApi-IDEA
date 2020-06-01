package com.huangpuweb.api.model;

import lombok.Data;

import java.io.Serializable;

/**                                                               l
 * 精炼和清洗后的人群态势数据表 sys_refined_crowdSituation
 */
@Data
public class SysRefinedCrowdSituation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer uuid;


    private String id;

    //景区名称
    private String spotName;

    //景区编码
    private String spotCode;

    //实时人流量
    private Integer realtimeNumber;

    //累计总人数
    private Integer totalNumber;

    //数据创建时间
    private String createTime;

    //同步数据时间
    private String updateTime;

    //数据来源
    private String dataSources;

    //备注
    private String remarks;

}
