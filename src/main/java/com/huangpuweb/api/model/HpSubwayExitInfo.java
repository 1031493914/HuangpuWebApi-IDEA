package com.huangpuweb.api.model;

import lombok.Data;

/**
 * created by Intellij IDEA
 *
 * @author Chengzhifeng
 * @date 2020/5/7
 * @statement
 */
@Data
public class HpSubwayExitInfo {


    private static final long serialVersionUID=1L;

    private String id;

    private String spotCode;

    private String subwayId;

    private Integer inTotalNum;

    private Integer outTotalNum;

    private String create_time;

    private String zdmc;


}
