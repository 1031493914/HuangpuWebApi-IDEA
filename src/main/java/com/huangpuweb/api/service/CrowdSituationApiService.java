package com.huangpuweb.api.service;

import com.huangpuweb.api.model.*;

import java.util.List;

public interface CrowdSituationApiService {


    SysRefinedCrowdSituation getAllDataRecordBack(String s);

    SysRefinedCrowdSituation getMaxNumberRecord(String todayZero, String s);

    SysHistoryCrowdSituation getYearAndMonthRecord(String s, String recordTime);

    CrowdSysSetting getCrowdSysSettingBySpotCode(String s);

    /**
     * @param spotCode
     * @param i
     * @return
     */
    SysHistoryCrowdSituation getSysHistoryCrowdSituationBySpotCode(String spotCode, int i);

    List<SysRefinedCrowdSituation> getListSysRefinedCrowdSituationByEveryHour(String todayZero, String today23, String s);

    HpSubwayExitInfo getHpSubwayExitInfo(String subwayId, String spotCode);

    SysHpParkInfo getSysHpParkInfo(String parkId);

    SysHpParkInfo getSysHpParkInfoByHour(String parkId, String s);
}
