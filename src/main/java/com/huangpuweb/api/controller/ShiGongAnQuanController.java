package com.huangpuweb.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huangpuweb.api.model.HpDianZhaoDianPaiInfo;
import com.huangpuweb.api.model.SysBuildingInfo;
import com.huangpuweb.api.model.SysBuildingStreetInfo;
import com.huangpuweb.api.service.ShiGongAnQuanApiService;
import com.huangpuweb.api.util.MapUtil;
import com.huangpuweb.api.util.StringUtil;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/shiGongAnQuan")
public class ShiGongAnQuanController {

    private static Logger logger = Logger.getLogger(ShiGongAnQuanController.class);

    private ShiGongAnQuanApiService shiGongAnQuanApiService;


    @Autowired
    public void setShiGongAnQuanApiService(ShiGongAnQuanApiService shiGongAnQuanApiService) {
        this.shiGongAnQuanApiService = shiGongAnQuanApiService;
    }


    /**
     * 电招电牌详情
     */
    @RequestMapping(value = "/getAllHpDianZhaoDianPaiInfo")
    public void getAllHpDianZhaoDianPaiInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllHpDianZhaoDianPaiInfo-----start-------");
        List<HpDianZhaoDianPaiInfo> listHpDianZhaoDianPaiInfo=shiGongAnQuanApiService.llistHpDianZhaoDianPaiInfo();
        for(HpDianZhaoDianPaiInfo ss:listHpDianZhaoDianPaiInfo){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }

        resultJson.put("listHpDianZhaoDianPaiInfo", listHpDianZhaoDianPaiInfo);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);

    }

    /**
     * 电招电牌图层显示
     */
    @RequestMapping(value = "/getDianZaoDianPaiMap")
    public void getDianZaoDianPaiMap(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getDianZaoDianPaiMap-----start-------");
        JSONObject ShiGongjsonObject = new JSONObject();
        //施工建筑
        Integer teamcount=shiGongAnQuanApiService.getDianZhaoDianpaiCount();
        ShiGongjsonObject.put("dianzhaodianpaicount",teamcount);
        resultJson.put("dianzhaodianpaicount",ShiGongjsonObject);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 图层数据
     */
    @RequestMapping(value = "/getShiGongMapEventItem")
    public void getShiGongMapEventItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getShiGongMapEventItem-----start-------");
        JSONObject ShiGongjsonObject = new JSONObject();
        //施工建筑
        Integer teamcount=shiGongAnQuanApiService.listShiGongCountByStreet("老西门街道");
        ShiGongjsonObject.put("shigoungbuildingcount",teamcount);
        resultJson.put("ShiGongjsonObject",ShiGongjsonObject);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 施工安全-工地数据概览
     */
    @RequestMapping(value = "/getShiGongAnQuanAllData")
    public void getShiGongAnQuanAllData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getShiGongAnQuanAllData-----start-------");
        //工地总数
        Integer buildingAllNum=shiGongAnQuanApiService.getBuildingAllNum();
        Integer buildingAllFocusNum=0;
        Integer buildingAllVideosNum=0;
        //工地所属街道
        List<SysBuildingStreetInfo>  listSysBuildingStreetInfo=new ArrayList<SysBuildingStreetInfo>();
//        List<SysBuildingStreetInfo>  listSysBuildingStreetInfo=shiGongAnQuanApiService.getListSysBuildingStreetInfo();
        resultJson.put("buildingAllNum", buildingAllNum);
        resultJson.put("buildingAllFocusNum", buildingAllFocusNum);
        resultJson.put("buildingAllVideosNum", buildingAllVideosNum);
        resultJson.put("listSysBuildingStreetInfo", listSysBuildingStreetInfo);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 施工安全页面数据返回-工地数据列表
     */
    @RequestMapping(value = "/getShiGongAnQuanBuildingList")
    public void getShiGongAnQuanBuildingList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=null;
        LogFactory.getLog(this.getClass()).info("------getShiGongAnQuanBuildingList-----start-------");
        //工地总数
        Integer buildingAllNum=shiGongAnQuanApiService.getBuildingAllNum();
        Integer buildingAllFocusNum=0;
        Integer buildingAllVideosNum=0;
        //工地所属街道
        List<SysBuildingInfo>  listSysBuildingInfo=shiGongAnQuanApiService.getShiGongAnQuanBuildingList();
        if(listSysBuildingInfo != null && listSysBuildingInfo.size()>0){
            for(SysBuildingInfo sysBuildingInfo:listSysBuildingInfo){
                jsonObject=new JSONObject();

                double[] map= MapUtil.bd09towgs84(Double.parseDouble(sysBuildingInfo.getLongitude()),Double.parseDouble(sysBuildingInfo.getLatitude()));
                jsonObject.put("Longitude",map[0]+"");
                jsonObject.put("Latitude",map[1]+"");
                jsonObject.put("buildingId",sysBuildingInfo.getId());
                jsonObject.put("name",sysBuildingInfo.getName());
                jsonObject.put("address",sysBuildingInfo.getAddress());
                jsonObject.put("principal",sysBuildingInfo.getPrincipal());
                jsonObject.put("connectmobile",sysBuildingInfo.getConnectmobile());
                jsonObject.put("usepeople",sysBuildingInfo.getUsepeople());
                jsonArray.add(jsonObject);
            }
        }
        resultJson.put("buildingAllNum", buildingAllNum);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", jsonArray);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 施工安全页面数据返回-工地数据详情
     */
    @RequestMapping(value = "/getShiGongAnQuanBuildingInfo")
    public void getShiGongAnQuanBuildingInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();

        String str = getRequestBodyString(req);
        if (StringUtil.isEmpty(str)) {
            responseErrorData("json error", resp);
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(str);
        //施工工地ID
        String buildingId = jsonObject.getString("buildingId");
        SysBuildingInfo sysBuildingInfo=shiGongAnQuanApiService.getSysBuildingInfoById(buildingId);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", sysBuildingInfo);
        responseData(resultObj.toString(), resp);
    }

    /**
     * 从req中读取数据
     *
     * @param req
     * @return
     */
    private String getRequestBodyString(HttpServletRequest req) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
        return builder.toString();
    }

    private void responseErrorData(String string, HttpServletResponse resp) throws Exception {
        // TODO Auto-generated method stub
        JSONObject resultObj = new JSONObject();
        resultObj.put("errorcode", 1);
        resultObj.put("message", string);
        responseData(resultObj.toString(), resp);
    }

    private void responseData(String string, HttpServletResponse resp) throws Exception {
        // TODO Auto-generated method stub
        responseDate("application/json;charset=UTF-8", string, resp);
    }

    private void responseDate(String contentType, String value, HttpServletResponse resp) throws Exception {
        resp.setContentType(contentType);
        resp.getWriter().write(value);
    }
}
