package com.huangpuweb.api.service.imp;

import com.huangpuweb.api.model.*;
import com.huangpuweb.api.service.GongGongAnQuanApiSysDataService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GongGongAnQuanApiServiceSysDataImp extends BaseSysDataServiceImp implements GongGongAnQuanApiSysDataService {


    @Override
    public List<SysHpEmergencyEvent> getListSysHpEmergencyEvent() {
        String sql="select * from hp_emergency_event order by startTime desc limit 5";
        return getEntryList(sql,new SysHpEmergencyEventRowMapper(),new Object[]{});
    }

    @Override
    public Integer getListSysHpEmergencyEventCountByYear(Date baseYear) {
        String sql="select count(id) from hp_emergency_event where startTime >= ?";
        return queryInteger(sql,new Object[]{baseYear});
    }

    @Override
    public List<SysHpEmergencyEvent> getListSysHpEmergencyEventByYear(Date baseYear) {
        String sql="select * from hp_emergency_event where startTime >= ?";
        return getEntryList(sql,new SysHpEmergencyEventRowMapper(),new Object[]{baseYear});
    }

    @Override
    public List<SysHpEmergencyEvent> getListSysHpEmergencyEventByMoreYear() {
        String sql="select * from hp_emergency_event ";
        return getEntryList(sql,new SysHpEmergencyEventRowMapper(),new Object[]{});
    }

    @Override
    public List<SysHpEmergencyEvent> getListSysHpEmergencyEvent20(Date yeartime) {
        String sql="select * from hp_emergency_event where startTime >= ?  order by startTime desc";
        return getEntryList(sql,new SysHpEmergencyEventRowMapper(),new Object[]{yeartime});
    }

    @Override
    public List<SysHpEmergencyTeam> listSysHpEmergencyTeamByType(int i) {
        String sql="select * from hp_emergency_team where teamtype = ?";
        return getEntryList(sql,new SysHpEmergencyTeamRowMapper(),new Object[]{i});
    }

    @Override
    public List<SysHpEmergencyImportArea> listSysHpEmergencyImportAreaByType(int i) {
        String sql="select * from hp_emergency_importarea where type = ?";
        return getEntryList(sql,new SysHpEmergencyImportAreaRowMapper(),new Object[]{i});
    }

    @Override
    public List<SysHpEmergencyDanger> llistSysHpEmergencyDangerByType(int i) {
        String sql="select * from hp_emergency_danger where type = ?";
        return getEntryList(sql,new SysHpEmergencyDangerRowMapper(),new Object[]{i});
    }

    @Override
    public List<SysHpEmergencyRefuge> llistSysHpEmergencyRefugeByType(int i) {
        String sql="select * from hp_emergency_refuge where type = ?";
        return getEntryList(sql,new SysHpEmergencyRefugeRowMapper(),new Object[]{i});
    }

    @Override
    public List<SysHpEmergencyProtect> llistSysHpEmergencyProtectByType(int i) {
        String sql="select * from hp_emergency_protect where type = ?";
        return getEntryList(sql,new SysHpEmergencyProtectRowMapper(),new Object[]{i});
    }

    @Override
    public List<SysHpEmergencyWaterengine> llistSysHpEmergencyWaterengineByType(int i,String string) {
        String sql="select * from hp_emergency_event_waterengine where type=?";
        return getEntryList(sql,new SysHpEmergencyWaterengineRowMapper(),new Object[]{string});
    }

    @Override
    public Integer listSysHpEmergencyTeamCount() {
        String sql="select count(1) from hp_emergency_team where teamtype = ?";
        return queryInteger(sql,new Object[]{1});
    }

    @Override
    public Integer listSysHpEmergencyImportAreaCount(String type) {
        String sql="select count(1) from hp_emergency_importarea where type = ?";
        return queryInteger(sql,new Object[]{type});
    }

    @Override
    public Integer listSysHpEmergencyDangerCount() {
        String sql="select count(1) from hp_emergency_danger where type = ?";
        return queryInteger(sql,new Object[]{1});
    }

    @Override
    public Integer listSysHpEmergencyRefugeCount() {
        String sql="select count(1) from hp_emergency_refuge where type = ?";
        return queryInteger(sql,new Object[]{1});
    }

    @Override
    public Integer listSysHpEmergencyProtectCount() {
        String sql="select count(1) from hp_emergency_protect where type = ?";
        return queryInteger(sql,new Object[]{1});
    }

    @Override
    public Integer listSysHpEmergencyWaterengineCount(String string) {
        String sql="select count(1) from hp_emergency_event_waterengine where type = ?";
        return queryInteger(sql,new Object[]{string});
    }

    @Override
    public Integer getListSysHpEmergencyEventCount(Date yeartime) {
        String sql="select count(1) from hp_emergency_event where startTime >= ? ";
        return queryInteger(sql,new Object[]{yeartime});
    }

    @Override
    public Integer getListFireControlCount() {
        String sql="select count(1) from hp_emergency_firecontrol ";
        return queryInteger(sql,new Object[]{});
    }

    @Override
    public List<SysHpEmergencyFireControl> listSysHpEmergencyFireControl() {
        String sql="select * from hp_emergency_firecontrol ";
        return getEntryList(sql,new SysHpEmergencyFireControlRowMapper(),new Object[]{});
    }

    @Override
    public List<SysHpEmergencyZhaMen> listSysHpEmergencyZhaMen() {
        String sql="select * from hp_emergency_zhamen ";
        return getEntryList(sql,new SysHpEmergencyZhaMenRowMapper(),new Object[]{});
    }

    @Override
    public Integer listZhaMenCount() {
        String sql="select count(1) from hp_emergency_zhamen ";
        return queryInteger(sql,new Object[]{});
    }

}
