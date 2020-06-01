package com.huangpuweb.api.service.imp;

import com.huangpuweb.api.model.*;
import com.huangpuweb.api.service.ShiGongAnQuanApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiGongAnQuanApiServiceImp extends BaseSysDataServiceImp implements ShiGongAnQuanApiService {


    @Override
    public Integer getBuildingAllNum() {
        String sql="select count(id) from sys_building_info";
        return queryInteger(sql,new Object[]{});
    }

    @Override
    public List<SysBuildingStreetInfo> getListSysBuildingStreetInfo() {
        String sql="select street,count(id) as buildingNum from sys_building_info group by street";
        return getEntryList(sql,new SysBuildingStreetInfoRowMapper(),new Object[]{});
    }

    @Override
    public List<SysBuildingInfo> getShiGongAnQuanBuildingList() {
        String sql="select * from sys_building_info where street=?";
        return getEntryList(sql,new SysBuildingInfoRowMapper(),new Object[]{"老西门街道"});
    }

    @Override
    public SysBuildingInfo getSysBuildingInfoById(String buildingId) {
        String sql="select * from sys_building_info where id=?";
        return getEntry(sql,new SysBuildingInfoRowMapper(),new Object[]{buildingId});
    }

    @Override
    public Integer listShiGongCountByStreet(String street) {
        String sql="select count(1) from sys_building_info where street=?";
        return queryInteger(sql,new Object[]{street});
    }

    @Override
    public Integer getDianZhaoDianpaiCount() {
        String sql="select count(1) from hp_dianzhaodianpai_info";
        return queryInteger(sql,new Object[]{});
    }

    @Override
    public List<HpDianZhaoDianPaiInfo> llistHpDianZhaoDianPaiInfo() {
        String sql="select * from hp_dianzhaodianpai_info ";
        return getEntryList(sql,new HpDianZhaoDianPaiInfoRowMapper(),new Object[]{});
    }


}
