package com.huangpuweb.api.service;

import com.huangpuweb.api.model.*;

import java.util.List;

public interface GongGongAnQuanApiService {


    Long getTeamGroupCount();

    List<LjErResourceTeam> getListLjErResourceTeam();

    Long getReservepointcount();

    List<LjErResourceGoods> getListLjErResourceGoods();

    Long getxpertgroupcount();

    List<LjErResourceExperts> getListLjErResourceExperts();

    Long getRiskcount();

    List<LjErDanger> getListLjErDanger();

    Long getrefugecount();

    List<LjErResourcePlace> getListLjErResourcePlace();

    Long getCasecount();

    Long getPlancount();

    Long getRepositorycount();

    List<LjErReservePlan> getListLjErReservePlan();

    List<LjErClassicalCase> getListLjErClassicalCase();

    List<LjErCommonSense> getListLjErCommonSense();

    List<SysHpEmergencyEvent> getListSysHpEmergencyEvent();
    List<SysHpEmergencyEvent> getListSysHpEmergencyEvent20(String yeartime);
}
