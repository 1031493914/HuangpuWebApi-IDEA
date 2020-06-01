package com.huangpuweb.api.service.imp;

import com.huangpuweb.api.model.*;
import com.huangpuweb.api.service.GongGongAnQuanApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GongGongAnQuanApiServiceImp extends BaseServiceImp implements GongGongAnQuanApiService {


    @Override
    public Long getTeamGroupCount() {
        String sql="select count(Guid) from lj_er_resource_team";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public List<LjErResourceTeam> getListLjErResourceTeam() {
        String sql="select * from lj_er_resource_team limit 10";
        return getEntryList(sql,new LjErResourceTeamRowMapper(),new Object[]{});
    }

    @Override
    public Long getReservepointcount() {
        String sql="select count(Guid) from lj_er_resource_goods";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public List<LjErResourceGoods> getListLjErResourceGoods() {
        String sql="select * from lj_er_resource_goods limit 10";
        return getEntryList(sql,new LjErResourceGoodsRowMapper(),new Object[]{});
    }

    @Override
    public Long getxpertgroupcount() {
        String sql="select count(Guid) from lj_er_resource_experts";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public List<LjErResourceExperts> getListLjErResourceExperts() {
        String sql="select * from lj_er_resource_experts limit 10";
        return getEntryList(sql,new LjErResourceExpertsRowMapper(),new Object[]{});
    }

    @Override
    public Long getRiskcount() {
        String sql="select count(Guid) from lj_er_danger";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public List<LjErDanger> getListLjErDanger() {
        String sql="select * from lj_er_danger limit 10";
        return getEntryList(sql,new LjErDangerRowMapper(),new Object[]{});
    }

    @Override
    public Long getrefugecount() {
        String sql="select count(Guid) from lj_er_resource_place";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public List<LjErResourcePlace> getListLjErResourcePlace() {
        String sql="select * from lj_er_resource_place limit 10";
        return getEntryList(sql,new LjErResourcePlaceRowMapper(),new Object[]{});
    }

    @Override
    public Long getCasecount() {
        String sql="select count(Guid) from lj_er_classical_case";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public Long getPlancount() {
        String sql="select count(Guid) from lj_er_reserve_plan";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public Long getRepositorycount() {
        String sql="select count(Guid) from lj_er_common_sense";
        return queryLong(sql,new Object[]{});
    }

    @Override
    public List<LjErReservePlan> getListLjErReservePlan() {
        String sql="select * from lj_er_reserve_plan limit 10";
        return getEntryList(sql,new LjErReservePlanRowMapper(),new Object[]{});
    }

    @Override
    public List<LjErClassicalCase> getListLjErClassicalCase() {
        String sql="select * from lj_er_classical_case limit 10";
        return getEntryList(sql,new LjErClassicalCaseRowMapper(),new Object[]{});
    }

    @Override
    public List<LjErCommonSense> getListLjErCommonSense() {
        String sql="select * from lj_er_common_sense limit 10";
        return getEntryList(sql,new LjErCommonSenseRowMapper(),new Object[]{});
    }

    @Override
    public List<SysHpEmergencyEvent> getListSysHpEmergencyEvent() {
        String sql="select * from lj_er_work_log_detail order by OccurTime desc limit 5";
        return getEntryList(sql,new ZyHpEmergencyEventRowMapper(),new Object[]{});
    }

    @Override
    public List<SysHpEmergencyEvent> getListSysHpEmergencyEvent20(String yeartime) {
        String sql="select * from lj_er_work_log_detail where OccurTime>=? order by OccurTime desc";
        return getEntryList(sql,new ZyHpEmergencyEventRowMapper(),new Object[]{yeartime});
    }
}
