package com.huangpuweb.api.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.huangpuweb.api.model.*;
import com.huangpuweb.api.service.GongGongAnQuanApiService;
import com.huangpuweb.api.service.GongGongAnQuanApiSysDataService;
import com.huangpuweb.api.util.DateUtil;
import com.huangpuweb.api.util.MapUtil;
import com.huangpuweb.api.util.StringUtil;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共安全模块
 */
@Controller
@RequestMapping(value = "/emergency")
public class GongGongAnQuanController {

    private static Logger logger = Logger.getLogger(GongGongAnQuanController.class);

    private GongGongAnQuanApiService gongGongAnQuanApiService;

    @Autowired
    public void setGongGongAnQuanApiService(GongGongAnQuanApiService gongGongAnQuanApiService) {
        this.gongGongAnQuanApiService = gongGongAnQuanApiService;
    }


    private GongGongAnQuanApiSysDataService gongGongAnQuanApiSysDataService;

    @Autowired
    public void setGongGongAnQuanApiSysDataService(GongGongAnQuanApiSysDataService gongGongAnQuanApiSysDataService) {
        this.gongGongAnQuanApiSysDataService = gongGongAnQuanApiSysDataService;
    }

    /**
     * 图层数据
     */
    @RequestMapping(value = "/getMapEventItem")
    public void getMapEventItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getMapEventItem-----start-------");
        JSONObject fangtaifangxunjsonObject=new JSONObject();
        JSONObject evnetjsonObject=new JSONObject();
        //抢险队伍
        Integer teamcount=gongGongAnQuanApiSysDataService.listSysHpEmergencyTeamCount();
        //重大险情
        Integer dangercount=gongGongAnQuanApiSysDataService.listSysHpEmergencyDangerCount();
        //撤离点
        Integer refugecount=gongGongAnQuanApiSysDataService.listSysHpEmergencyRefugeCount();
        //中考防汛点
        Integer protectcount=gongGongAnQuanApiSysDataService.listSysHpEmergencyProtectCount();
        //微型泵站
        Integer waterenginecount=gongGongAnQuanApiSysDataService.listSysHpEmergencyWaterengineCount("小包围设施");
        //大型泵站
        Integer largeWaterenginecount=gongGongAnQuanApiSysDataService.listSysHpEmergencyWaterengineCount("市级");
        //年度事件数
        Integer yearEventCount=gongGongAnQuanApiSysDataService.getListSysHpEmergencyEventCount(DateUtil.getCurrYearFirst());
        //消防站数
        Integer firecontrolCount=gongGongAnQuanApiSysDataService.getListFireControlCount();
        //重点区域-地铁
        Integer importareasubwaycount=gongGongAnQuanApiSysDataService.listSysHpEmergencyImportAreaCount("1");
        //重点区域-隧道
        Integer importareasuidaocount=gongGongAnQuanApiSysDataService.listSysHpEmergencyImportAreaCount("3");
        //重点区域-立交
        Integer importarealijiaocount=gongGongAnQuanApiSysDataService.listSysHpEmergencyImportAreaCount("2");
        //闸门数据
        Integer zhamencount=gongGongAnQuanApiSysDataService.listZhaMenCount();


        evnetjsonObject.put("yearEventCount",yearEventCount);
        fangtaifangxunjsonObject.put("teamcount",teamcount);
        fangtaifangxunjsonObject.put("importareacount",importareasubwaycount);
        fangtaifangxunjsonObject.put("importareasuidaocount",importareasuidaocount);
        fangtaifangxunjsonObject.put("zhamencount",zhamencount);
        fangtaifangxunjsonObject.put("importarealijiaocount",importarealijiaocount);
        fangtaifangxunjsonObject.put("dangercount",dangercount);
        fangtaifangxunjsonObject.put("refugecount",refugecount);
        fangtaifangxunjsonObject.put("protectcount",protectcount);
        fangtaifangxunjsonObject.put("waterenginecount",waterenginecount);
        fangtaifangxunjsonObject.put("largeWaterenginecount",largeWaterenginecount);
        fangtaifangxunjsonObject.put("historycount","历史沿革");
        fangtaifangxunjsonObject.put("firecontrolCount",firecontrolCount);
        resultJson.put("evnetjsonObject",evnetjsonObject);
        resultJson.put("fangtaifangxunjsonObject",fangtaifangxunjsonObject);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }

    /**
     * 闸门数据
     */
    @RequestMapping(value = "/getAllEmergencyZhaMen")
    public void getAllEmergencyZhaMen(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyZhaMen-----start-------");
        List<SysHpEmergencyZhaMen> listSysHpEmergencyZhaMen=gongGongAnQuanApiSysDataService.listSysHpEmergencyZhaMen();
        for(SysHpEmergencyZhaMen ss:listSysHpEmergencyZhaMen){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyZhaMen", listSysHpEmergencyZhaMen);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 消防站数
     */
    @RequestMapping(value = "/getAllEmergencyFireControlStation")
    public void getAllEmergencyFireControlStation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyFireControlStation-----start-------");
        List<SysHpEmergencyFireControl> listSysHpEmergencyFireControl=gongGongAnQuanApiSysDataService.listSysHpEmergencyFireControl();
        for(SysHpEmergencyFireControl ss:listSysHpEmergencyFireControl){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyFireControl", listSysHpEmergencyFireControl);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }

    /**
     * 防台防汛专业队伍获取
     */
    @RequestMapping(value = "/getAllEmergencyEventTeam")
    public void getAllEmergencyEventTeam(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventTeam-----start-------");
        List<SysHpEmergencyTeam> listSysHpEmergencyTeam=gongGongAnQuanApiSysDataService.listSysHpEmergencyTeamByType(1);
        for(SysHpEmergencyTeam ss:listSysHpEmergencyTeam){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyTeam", listSysHpEmergencyTeam);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);


    }

    /**
     * 防台防汛重点区域数据 立交
     */
    @RequestMapping(value = "/getAllEmergencyEventLijiao")
    public void getAllEmergencyEventLijiao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventLijiao-----start-------");
        List<SysHpEmergencyImportArea> listSysHpEmergencyImportArea=gongGongAnQuanApiSysDataService.listSysHpEmergencyImportAreaByType(2);
        for(SysHpEmergencyImportArea ss:listSysHpEmergencyImportArea){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyImportArea", listSysHpEmergencyImportArea);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);

    }

    /**
     * 防台防汛重点区域数据 隧道
     */
    @RequestMapping(value = "/getAllEmergencyEventSuiDao")
    public void getAllEmergencyEventSuiDao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventSuiDao-----start-------");
        List<SysHpEmergencyImportArea> listSysHpEmergencyImportArea=gongGongAnQuanApiSysDataService.listSysHpEmergencyImportAreaByType(3);
        for(SysHpEmergencyImportArea ss:listSysHpEmergencyImportArea){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyImportArea", listSysHpEmergencyImportArea);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);

    }

    /**
     * 防台防汛重点区域数据 地铁
     */
    @RequestMapping(value = "/getAllEmergencyEventSubway")
    public void getAllEmergencyEventSubway(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventTeam-----start-------");
        List<SysHpEmergencyImportArea> listSysHpEmergencyImportArea=gongGongAnQuanApiSysDataService.listSysHpEmergencyImportAreaByType(1);
        for(SysHpEmergencyImportArea ss:listSysHpEmergencyImportArea){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyImportArea", listSysHpEmergencyImportArea);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 防台防汛重大险情
     */
    @RequestMapping(value = "/getAllEmergencyEventDanger")
    public void getAllEmergencyEventDanger(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventDanger-----start-------");
        List<SysHpEmergencyDanger> listSysHpEmergencyDanger=gongGongAnQuanApiSysDataService.llistSysHpEmergencyDangerByType(1);
        for(SysHpEmergencyDanger ss:listSysHpEmergencyDanger){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyDanger", listSysHpEmergencyDanger);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);

    }



    /**
     * 防台防汛撤离点信息
     */
    @RequestMapping(value = "/getAllEmergencyEventSafePlace")
    public void getAllEmergencyEventSafePlace(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventSafePlace-----start-------");
        List<SysHpEmergencyRefuge> listSysHpEmergencyRefuge=gongGongAnQuanApiSysDataService.llistSysHpEmergencyRefugeByType(1);
        for(SysHpEmergencyRefuge ss:listSysHpEmergencyRefuge){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyRefuge", listSysHpEmergencyRefuge);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);


    }

    /**
     * 防台防汛中考防汛
     */
    @RequestMapping(value = "/getAllEmergencyEventPortect")
    public void getAllEmergencyEventPortect(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventPortect-----start-------");
        List<SysHpEmergencyProtect> listSysHpEmergencyProtect=gongGongAnQuanApiSysDataService.llistSysHpEmergencyProtectByType(1);
        for(SysHpEmergencyProtect ss:listSysHpEmergencyProtect){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }
        resultJson.put("listSysHpEmergencyProtect", listSysHpEmergencyProtect);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);


    }

    /**
     * 防台防汛泵站-小包围
     */
    @RequestMapping(value = "/getAllEmergencyEventWaterEngine")
    public void getAllEmergencyEventWaterEngine(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventWaterEngine-----start-------");
        List<SysHpEmergencyWaterengine> listSysHpEmergencyWaterengine=gongGongAnQuanApiSysDataService.llistSysHpEmergencyWaterengineByType(1,"小包围设施");
        for(SysHpEmergencyWaterengine ss:listSysHpEmergencyWaterengine){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }

        resultJson.put("listSysHpEmergencyWaterengine", listSysHpEmergencyWaterengine);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);


    }


    /**
     * 防台防汛大型泵站
     */
    @RequestMapping(value = "/getAllEmergencyEventlargeWater")
    public void getAllEmergencyEventlargeWater(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEventlargeWater-----start-------");
        List<SysHpEmergencyWaterengine> listSysHpEmergencyWaterengine=gongGongAnQuanApiSysDataService.llistSysHpEmergencyWaterengineByType(1,"市级");
        for(SysHpEmergencyWaterengine ss:listSysHpEmergencyWaterengine){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(ss.getBaiduLongitude()),Double.parseDouble(ss.getBaiduLatitude()));
            ss.setBaiduLongitude(map[0]+"");
            ss.setBaiduLatitude(map[1]+"");
        }

        resultJson.put("listSysHpEmergencyWaterengine", listSysHpEmergencyWaterengine);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);

    }
    /**
     * 全区事件分析
     */
    @RequestMapping(value = "/getAllEmergencyEvent")
    public void getAllEmergencyEvent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllEmergencyEvent-----start-------");
        Date baseYear= DateUtil.getCurrYearFirst();
        Integer listSysHpEmergencyEventCount=gongGongAnQuanApiSysDataService.getListSysHpEmergencyEventCountByYear(baseYear);

        resultJson.put("alleventcount", listSysHpEmergencyEventCount);
        resultJson.put("yeseventcount", listSysHpEmergencyEventCount);
        resultJson.put("noeventcount", 0);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);


    }


    /**
     * 本年度事件发生趋势 按月份
     */
    @RequestMapping(value = "/getEmergencyEventCountByMonth")
    public void getEmergencyEventCountByMonth(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getEmergencyEventCountByMonth-----start-------");
        Date baseYear= DateUtil.getCurrYearFirst();
        List<SysHpEmergencyEvent>  listSysHpEmergencyEvent=gongGongAnQuanApiSysDataService.getListSysHpEmergencyEventByYear(baseYear);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Integer> houMap=new HashMap<String,Integer>();
        for(SysHpEmergencyEvent oness:listSysHpEmergencyEvent){

            try{
                if(oness.getStartTime() != null){
                    String createTime = sdf.format(oness.getStartTime());
                    String month = DateUtil.getMonth(createTime);
                    if(StringUtil.isNotEmpty(month)){
                        if(houMap.get(month)!=null){
                            houMap.put(month,houMap.get(month)+1);
                        }else{
                            houMap.put(month,1);
                        }
                    }
                }
            }catch(Exception e){
                continue;
            }


//            if(houMap.get(month)!=null){
//                houMap.put(month,houMap.get(month)+1);
//            }else{
//                houMap.put(month,1);
//            }


        }

        JSONArray ssArray=new JSONArray();
        JSONObject ssjson=null;
        Boolean flag=false;
        for (Map.Entry<String, Integer> entry : houMap.entrySet()) {
            ssjson=new JSONObject();
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            ssjson.put("time",entry.getKey());
            ssjson.put("value",entry.getValue());
            ssArray.add(ssjson);

        }
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", ssArray);
        responseData(resultObj.toString(), resp);
    }



    /**
     * 本年度事件发生趋势 按事件类型
     */
    @RequestMapping(value = "/getEmergencyEventCountByType")
    public void getEmergencyEventCountByType(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getEmergencyEventCountByType-----start-------");
        Date baseYear= DateUtil.getCurrYearFirst();
        List<SysHpEmergencyEvent>  listSysHpEmergencyEvent=gongGongAnQuanApiSysDataService.getListSysHpEmergencyEventByYear(baseYear);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Integer> houMap=new HashMap<String,Integer>();
        for(SysHpEmergencyEvent oness:listSysHpEmergencyEvent){

            if (oness.getEventType().equals("290ef77a-7c02-41c1-a279-5e2ce0a45340")) {

                oness.setEventType("事故灾难");

            } else if (oness.getEventType().equals("3d93e3d7-ba1d-4442-b825-44333b66290a")) {
                oness.setEventType("其他");
                //1e1b5957-5818-4a0f-8f99-eb9ce46b3e51
            } else if (oness.getEventType().equals("1e1b5957-5818-4a0f-8f99-eb9ce46b3e51")) {
                //eventType = "自然灾害";
                oness.setEventType("自然灾害");

            } else if (oness.getEventType().equals("348be4c1-46ea-47c1-a0c8-2c7175a7a890")) {
//                eventType = "社会安全";
                oness.setEventType("社会安全");

            } else if (oness.getEventType().equals("2b80b610-461c-4389-bf61-49ba17b85f0d")) {
//                eventType = "公共卫生";
                oness.setEventType("公共卫生");

            }
            if(oness.getEventType().length()>7){
                if(houMap.get("其他")!=null) {
                    houMap.put("其他", houMap.get("其他") + 1);
                }else{
                    houMap.put("其他",1);
                }
                continue;
            }
            if(houMap.get(oness.getEventType())!=null){

                    houMap.put(oness.getEventType(),houMap.get(oness.getEventType())+1);


            }else{
                houMap.put(oness.getEventType(),1);
            }
        }

        JSONArray ssArray=new JSONArray();
        JSONObject ssjson=null;
        Boolean flag=false;
        for (Map.Entry<String, Integer> entry : houMap.entrySet()) {
            ssjson=new JSONObject();
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            ssjson.put("type",entry.getKey());
            ssjson.put("value",entry.getValue());
            ssArray.add(ssjson);
        }
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", ssArray);
        responseData(resultObj.toString(), resp);
    }



       /**
     * 本年度事件发生趋势 按年查询
     */
    @RequestMapping(value = "/getEmergencyEventCountByYear")
    public void getEmergencyEventCountByYear(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getEmergencyEventCountByYear-----start-------");
//        Date baseYear= DateUtil.getCurrYearFirst();
        List<SysHpEmergencyEvent>  listSysHpEmergencyEvent=gongGongAnQuanApiSysDataService.getListSysHpEmergencyEventByMoreYear();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Integer> houMap=new HashMap<String,Integer>();
        for(SysHpEmergencyEvent oness:listSysHpEmergencyEvent){
            try{
            String createTime = sdf.format(oness.getStartTime());
            if(StringUtil.isNotEmpty(createTime)){

                    String year = DateUtil.getYear(createTime);
                    if(houMap.get(year)!=null){
                        houMap.put(year,houMap.get(year)+1);
                    }else{
                        houMap.put(year,1);
                    }


            }
            }catch (Exception e){
                continue;
            }





        }

        JSONArray ssArray=new JSONArray();
        JSONObject ssjson=null;
        Boolean flag=false;
        for (Map.Entry<String, Integer> entry : houMap.entrySet()) {
            ssjson=new JSONObject();
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            ssjson.put("time",entry.getKey());
            ssjson.put("value",entry.getValue());
            ssArray.add(ssjson);

        }
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", ssArray);
        responseData(resultObj.toString(), resp);
    }







    /**
     * 公共安全模块-应急资源
     * teamgroupcount	应急队伍数量
     * reservepointcount	储备点数量
     * expertgroupcount	专家组数量
     * riskcount	危险源数量
     * refugecount	避难场所数量
     * casecount	案例库数量
     * plancount	预案库数量
     * repositorycount	知识库数量
     */
    @RequestMapping(value = "/getEmergencyResource")
    public void getEmergencyResource(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getEmergencyResource-----start-------");

        Long teamgroupcount = gongGongAnQuanApiService.getTeamGroupCount();
        resultJson.put("teamgroupcount", teamgroupcount);

        //储备点数量 reservepointcount lj_er_resource_goods 应急资源物资
        Long reservepointcount = gongGongAnQuanApiService.getReservepointcount();
        resultJson.put("reservepointcount", reservepointcount);

        //专家组数量 expertgroupcount lj_er_resource_experts
        Long expertgroupcount = gongGongAnQuanApiService.getxpertgroupcount();
        resultJson.put("expertgroupcount", expertgroupcount);


        //危险源数量 riskcount lj_er_danger
        Long riskcount = gongGongAnQuanApiService.getRiskcount();
        resultJson.put("riskcount", riskcount);


        //避难场所数量 refugecount  lj_er_resource_place
        Long refugecount = gongGongAnQuanApiService.getrefugecount();
        resultJson.put("refugecount", refugecount);


        //案例库数量 casecount lj_er_classical_case
        Long casecount = gongGongAnQuanApiService.getCasecount();
        resultJson.put("casecount", casecount);


        //预案库数量 plancount lj_er_reserve_plan
        Long plancount = gongGongAnQuanApiService.getPlancount();
        resultJson.put("plancount", plancount);
        //知识库数量 repositorycount lj_er_common_sense
        Long repositorycount = gongGongAnQuanApiService.getRepositorycount();
        resultJson.put("repositorycount", repositorycount);

        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);

    }


    /**
     * 公共安全模块-应急资源详情根据Type获取相关详情
     * 0	应急队伍列表
     * 1	储备点列表
     * 2	专家组列表
     * 3	危险源列表
     * 4	避难场所列表
     * 5	案例库列表
     * 6	预案库列表
     * 7	知识库列表
     */
    @RequestMapping(value = "/getEmergencyResourceDetail")
    public void getEmergencyResourceDetail(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getEmergencyResourceDetail-----start-------");
        String str = getRequestBodyString(req);
        if (StringUtil.isEmpty(str)) {
            responseErrorData("json error", resp);
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(str);
        // 应急队伍数量
        String type = jsonObject.getString("type");
        if (type.equals("0")) {
            List<LjErResourceTeam> listLjErResourceTeam = gongGongAnQuanApiService.getListLjErResourceTeam();
            if (listLjErResourceTeam != null && listLjErResourceTeam.size() > 0) {
                resultJson.put("listLjErResourceTeam", listLjErResourceTeam);
            } else {
                resultJson.put("listLjErResourceTeam", "");
            }
        }
        //专家组数量
        if (type.equals("1")) {
            List<LjErResourceExperts> listLjErResourceExperts = gongGongAnQuanApiService.getListLjErResourceExperts();
            if (listLjErResourceExperts != null && listLjErResourceExperts.size() > 0) {
                resultJson.put("listLjErResourceExperts", listLjErResourceExperts);
            } else {
                resultJson.put("listLjErResourceExperts", "");
            }
        }
        //储备点数量
        if (type.equals("2")) {
            List<LjErResourceGoods> listLjErResourceGoods = gongGongAnQuanApiService.getListLjErResourceGoods();
            if (listLjErResourceGoods != null && listLjErResourceGoods.size() > 0) {
                for(LjErResourceGoods lj:listLjErResourceGoods){
                    double[] map= MapUtil.bd09towgs84(Double.parseDouble(lj.getBaiduLongitude()),Double.parseDouble(lj.getBaiduLatitude()));
                    lj.setBaiduLongitude(map[0]+"");
                    lj.setBaiduLatitude(map[1]+"");
                }

                resultJson.put("listLjErResourceGoods", listLjErResourceGoods);
            } else {
                resultJson.put("listLjErResourceGoods", "");
            }
        }
        //危险源数量
        if (type.equals("3")) {
            List<LjErDanger> listLjErDanger = gongGongAnQuanApiService.getListLjErDanger();
            if (listLjErDanger != null && listLjErDanger.size() > 0) {
                for(LjErDanger lj:listLjErDanger){
                    double[] map= MapUtil.bd09towgs84(Double.parseDouble(lj.getBaiduLongitude()),Double.parseDouble(lj.getBaiduLatitude()));
                    lj.setBaiduLongitude(map[0]+"");
                    lj.setBaiduLatitude(map[1]+"");
                }
                resultJson.put("listLjErDanger", listLjErDanger);
            } else {
                resultJson.put("listLjErDanger", "");
            }
        }
        //避难场所数量
        if (type.equals("4")) {
            List<LjErResourcePlace> listLjErResourcePlace = gongGongAnQuanApiService.getListLjErResourcePlace();
            if (listLjErResourcePlace != null && listLjErResourcePlace.size() > 0) {
                for(LjErResourcePlace lj:listLjErResourcePlace){
                    double[] map= MapUtil.bd09towgs84(Double.parseDouble(lj.getBaiduLongitude()),Double.parseDouble(lj.getBaiduLatitude()));
                    lj.setBaiduLongitude(map[0]+"");
                    lj.setBaiduLatitude(map[1]+"");
                }
                resultJson.put("listLjErResourcePlace", listLjErResourcePlace);
            } else {
                resultJson.put("listLjErResourcePlace", "");
            }
        }

        //案例库数量
        if (type.equals("5")) {
            List<LjErClassicalCase> listLjErClassicalCase = gongGongAnQuanApiService.getListLjErClassicalCase();
            if (listLjErClassicalCase != null && listLjErClassicalCase.size() > 0) {
                resultJson.put("listLjErClassicalCase", listLjErClassicalCase);
            } else {
                resultJson.put("listLjErClassicalCase", "");
            }
        }

        //预案库数量
        if (type.equals("6")) {
            List<LjErReservePlan> listLjErReservePlan = gongGongAnQuanApiService.getListLjErReservePlan();
            if (listLjErReservePlan != null && listLjErReservePlan.size() > 0) {
                resultJson.put("listLjErReservePlan", listLjErReservePlan);
            } else {
                resultJson.put("listLjErReservePlan", "");
            }
        }

        //知识库数量
        if (type.equals("7")) {
            List<LjErCommonSense> listLjErCommonSense = gongGongAnQuanApiService.getListLjErCommonSense();
            if (listLjErCommonSense != null && listLjErCommonSense.size() > 0) {
                resultJson.put("listLjErCommonSense", listLjErCommonSense);
            } else {
                resultJson.put("listLjErCommonSense", "");
            }
        }

        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);

    }


    /**
     * 公共安全模块-Top5事件列表
     * hp_emergency_event
     */
    @RequestMapping(value = "/getTop5EventList")
    public void getTop5EventList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 创建HttpClient实例
        HttpClient client = HttpClientBuilder.create().build();
        JSONArray ja = new JSONArray();
        JSONObject jo = null;
        JSONObject locationjson = null;
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getTop5EventList-----start-------");
        List<SysHpEmergencyEvent> listSysHpEmergencyEvent = gongGongAnQuanApiService.getListSysHpEmergencyEvent();
        String eventType="";
        for(SysHpEmergencyEvent sysHpEmergencyEvent :listSysHpEmergencyEvent){


            try {
                // 根据URL创建HttpGet实例
                HttpGet get = new HttpGet("http://173.82.57.8:8070/geo-server/public/rest/geocoding/generalSearch?queryStr=上海市黄浦区" +sysHpEmergencyEvent.getAddress());
                // 执行get请求，得到返回体
                HttpResponse response = client.execute(get);
                // 判断是否正常返回
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 解析数据
                    String data = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
                    ja = JSONArray.parseArray(data);
                    jo = ja.getJSONObject(0);
                    locationjson = jo.getJSONObject("location");
                    System.out.println(sysHpEmergencyEvent.getEventName() + "----" + locationjson.getString("x") + "------" + locationjson.getString("y"));
                    sysHpEmergencyEvent.setBaiduLongitude(locationjson.getString("x"));
                    sysHpEmergencyEvent.setBaiduLatitude(locationjson.getString("y"));
                    if(sysHpEmergencyEvent.getEventType().equals("290ef77a-7c02-41c1-a279-5e2ce0a45340")){

                        eventType="事故灾难";

                    }else if(sysHpEmergencyEvent.getEventType().equals("3d93e3d7-ba1d-4442-b825-44333b66290a")){
                        eventType="其他";

                    }else if(sysHpEmergencyEvent.getEventType().equals("1e1b5957-5818-4a0f-8f99-eb9ce46b3e51")){
                        eventType="自然灾害";

                    }else if(sysHpEmergencyEvent.getEventType().equals("348be4c1-46ea-47c1-a0c8-2c7175a7a890")){
                        eventType="社会安全";

                    }else if(sysHpEmergencyEvent.getEventType().equals("2b80b610-461c-4389-bf61-49ba17b85f0d")){
                        eventType="公共卫生";

                    }

                    sysHpEmergencyEvent.setEventType(eventType);
                }
            } catch (Exception e) {
                continue;
            }





        }
        resultJson.put("listSysHpEmergencyEvent", listSysHpEmergencyEvent);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }



    /**
     * 公共安全模块-当年事件列表
     * hp_emergency_event
     */
    @RequestMapping(value = "/getTopYearEventList")
    public void getTopYearEventList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 创建HttpClient实例
        HttpClient client = HttpClientBuilder.create().build();

        JSONArray ja = new JSONArray();
        JSONObject jo = null;
        JSONObject locationjson = null;
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getTopYearEventList-----start-------");
        List<SysHpEmergencyEvent> listSysHpEmergencyEvent = gongGongAnQuanApiSysDataService.getListSysHpEmergencyEvent20(DateUtil.getCurrYearFirst());
//        List<SysHpEmergencyEvent> listSysHpEmergencyEvent = gongGongAnQuanApiService.getListSysHpEmergencyEvent20(yeartime);
        String eventType="";
        for(SysHpEmergencyEvent sysHpEmergencyEvent :listSysHpEmergencyEvent){
            double[] map= MapUtil.bd09towgs84(Double.parseDouble(sysHpEmergencyEvent.getBaiduLongitude()),Double.parseDouble(sysHpEmergencyEvent.getBaiduLatitude()));
            sysHpEmergencyEvent.setBaiduLongitude(map[0]+"");
            sysHpEmergencyEvent.setBaiduLatitude(map[1]+"");

            if(sysHpEmergencyEvent.getEventType().equals("290ef77a-7c02-41c1-a279-5e2ce0a45340")){

                eventType="事故灾难";

            }else if(sysHpEmergencyEvent.getEventType().equals("3d93e3d7-ba1d-4442-b825-44333b66290a")){
                eventType="其他";

            }else if(sysHpEmergencyEvent.getEventType().equals("1e1b5957-5818-4a0f-8f99-eb9ce46b3e51")){
                eventType="自然灾害";

            }else if(sysHpEmergencyEvent.getEventType().equals("348be4c1-46ea-47c1-a0c8-2c7175a7a890")){
                eventType="社会安全";

            }else if(sysHpEmergencyEvent.getEventType().equals("2b80b610-461c-4389-bf61-49ba17b85f0d")){
                eventType="公共卫生";

            }

            sysHpEmergencyEvent.setEventType(eventType);


        }
        resultJson.put("listSysHpEmergencyEvent", listSysHpEmergencyEvent);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
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
