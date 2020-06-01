package com.huangpuweb.api.service.imp;

import com.huangpuweb.api.model.*;
import com.huangpuweb.api.service.CrowdSituationApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrowdSituationApiServiceImp extends BaseSysDataServiceImp implements CrowdSituationApiService {


    @Override
    public SysRefinedCrowdSituation getAllDataRecordBack(String s) {

        String sql="select * from sys_refined_crowdSituation where spotCode =? order  by createTime desc LIMIT 0 ,1";
        return getEntry(sql,new SysRefinedCrowdSituationRowMapper(),new Object[]{s});
    }

    @Override
    public SysRefinedCrowdSituation getMaxNumberRecord(String todayZero, String s) {
        String sql="select *  from sys_refined_crowdSituation where spotcode=?  and createTime >=? order by realtimeNumber desc limit 0,1";
        return getEntry(sql,new SysRefinedCrowdSituationRowMapper(),new Object[]{s,todayZero});
    }

    @Override
    public SysHistoryCrowdSituation getYearAndMonthRecord(String s, String recordTime) {
        String sql="select * from sys_history_crowdSituation where spotCode=? and  recordTime=?";
        return getEntry(sql,new SysHistoryCrowdSituationRowMapper(),new Object[]{s,recordTime});
    }

    @Override
    public CrowdSysSetting getCrowdSysSettingBySpotCode(String s) {
        String sql="select * from crowd_sys_setting where spotcode=?";
        return getEntry(sql,new CrowdSysSettingRowMapper(),new Object[]{s});
    }

    @Override
    public SysHistoryCrowdSituation getSysHistoryCrowdSituationBySpotCode(String spotCode, int i) {
        String sql="select * from sys_history_crowdSituation where spotCode= ?  order by recordTime desc limit ?,?";
        return getEntry(sql,new SysHistoryCrowdSituationRowMapper(),new Object[]{spotCode,i,1});
    }

    @Override
    public List<SysRefinedCrowdSituation> getListSysRefinedCrowdSituationByEveryHour(String todayZero, String today23, String s) {
        String sql="select * from sys_refined_crowdSituation where spotCode=? and createTime <=? and createTime >=? ORDER BY createTime ASC";
        return getEntryList(sql,new SysRefinedCrowdSituationRowMapper(),new Object[]{s,today23,todayZero});
    }

    @Override
    public HpSubwayExitInfo getHpSubwayExitInfo(String subwayId, String spotCode) {
//        String sql="select * from hp_subway_exit_sys where spotCode= ?  and  subwayId=? order by id desc";
//        return getEntry(sql,new HpSubWayExitInfoRowMapper(),new Object[]{spotCode,subwayId});
        String sql="select * from hp_subway_exit_sys where spotCode= ? order by id desc";
        return getEntry(sql,new HpSubWayExitInfoRowMapper(),new Object[]{spotCode});
    }

    @Override
    public SysHpParkInfo getSysHpParkInfo(String parkId) {
        String sql="select * from hp_park_info where parkId=? order by recordTime limit 0,1";
        return getEntry(sql,new SysHpParkInfoRowMapper(),new Object[]{parkId});
    }

    @Override
    public SysHpParkInfo getSysHpParkInfoByHour(String parkId, String s) {
        String sql="select parkingNumber from hp_park_info where parkId=? and  recordTime like "+s;
        return getEntry(sql,new SysHpParkInfoRowMapper(),new Object[]{parkId});
    }
}
