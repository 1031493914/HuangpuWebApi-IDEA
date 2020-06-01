package com.huangpuweb.api.service;

import com.huangpuweb.api.model.*;

import java.util.Date;
import java.util.List;

public interface GongGongAnQuanApiSysDataService {


    List<SysHpEmergencyEvent> getListSysHpEmergencyEvent();

    Integer  getListSysHpEmergencyEventCountByYear(Date baseYear);

    List<SysHpEmergencyEvent> getListSysHpEmergencyEventByYear(Date baseYear);

    List<SysHpEmergencyEvent> getListSysHpEmergencyEventByMoreYear();

    List<SysHpEmergencyEvent> getListSysHpEmergencyEvent20(Date yeartime);

    List<SysHpEmergencyTeam> listSysHpEmergencyTeamByType(int i);

    List<SysHpEmergencyImportArea> listSysHpEmergencyImportAreaByType(int i);

    List<SysHpEmergencyDanger> llistSysHpEmergencyDangerByType(int i);

    List<SysHpEmergencyRefuge> llistSysHpEmergencyRefugeByType(int i);

    List<SysHpEmergencyProtect> llistSysHpEmergencyProtectByType(int i);

    List<SysHpEmergencyWaterengine> llistSysHpEmergencyWaterengineByType(int i,String string);

    Integer listSysHpEmergencyTeamCount();

    Integer listSysHpEmergencyImportAreaCount(String type);

    Integer listSysHpEmergencyDangerCount();

    Integer listSysHpEmergencyRefugeCount();

    Integer listSysHpEmergencyProtectCount();

    Integer listSysHpEmergencyWaterengineCount(String string);

    Integer getListSysHpEmergencyEventCount(Date currYearFirst);

    Integer getListFireControlCount();

    List<SysHpEmergencyFireControl> listSysHpEmergencyFireControl();

    List<SysHpEmergencyZhaMen> listSysHpEmergencyZhaMen();

    Integer listZhaMenCount();
}
