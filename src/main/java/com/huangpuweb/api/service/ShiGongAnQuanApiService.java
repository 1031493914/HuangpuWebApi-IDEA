package com.huangpuweb.api.service;

import com.huangpuweb.api.model.HpDianZhaoDianPaiInfo;
import com.huangpuweb.api.model.SysBuildingInfo;
import com.huangpuweb.api.model.SysBuildingStreetInfo;

import java.util.List;

public interface ShiGongAnQuanApiService {


    Integer getBuildingAllNum();

    List<SysBuildingStreetInfo> getListSysBuildingStreetInfo();

    List<SysBuildingInfo> getShiGongAnQuanBuildingList();

    SysBuildingInfo getSysBuildingInfoById(String buildingId);

    Integer listShiGongCountByStreet(String street);

    Integer getDianZhaoDianpaiCount();

    List<HpDianZhaoDianPaiInfo> llistHpDianZhaoDianPaiInfo();
}
