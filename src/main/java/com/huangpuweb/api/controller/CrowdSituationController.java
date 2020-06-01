package com.huangpuweb.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huangpuweb.api.Constants;
import com.huangpuweb.api.model.*;
import com.huangpuweb.api.service.CrowdSituationApiService;
import com.huangpuweb.api.util.DateUtil;
import com.huangpuweb.api.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/crowd")
public class CrowdSituationController {

    private static Logger logger = Logger.getLogger(CrowdSituationController.class);

    private CrowdSituationApiService crowdSituationApiService;

    @Autowired
    public void setCrowdSituationApiService(CrowdSituationApiService crowdSituationApiService) {
        this.crowdSituationApiService = crowdSituationApiService;
    }


    /**
     * 根据停车场id和景区编码，获取景区周边停车场实时数
     * @return List<Record>
     */
    @RequestMapping(value = "/getParkInfoRealTimeData")
    public void getParkInfoRealTimeData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();

        LogFactory.getLog(this.getClass()).info("------getParkInfoRealTimeData-----start-------");
        String str = getRequestBodyString(req);
        if (StringUtil.isEmpty(str)) {
            responseErrorData("json error", resp);
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(str);

        String parkId = jsonObject.getString("parkId");

        String spotCode = jsonObject.getString("spotCode");
        JSONObject sysHpParkjson=new JSONObject();
        SysHpParkInfo sysHpParkInfo=crowdSituationApiService.getSysHpParkInfo(parkId);
        sysHpParkjson.put("parkid",sysHpParkInfo.getParkId());
        sysHpParkjson.put("parkingnumber",sysHpParkInfo.getParkingNumber());
        sysHpParkjson.put("total",sysHpParkInfo.getTotal());
        sysHpParkjson.put("total_left",sysHpParkInfo.getTotal_left());



        JSONObject xjson=null;
        JSONObject yjson=null;
        JSONArray jsonxArray=new JSONArray();
        JSONArray jsonyArray=new JSONArray();
        for (int i = 0; i < 6; i++) {
            xjson=new JSONObject();
            yjson=new JSONObject();
            String lastHour = DateUtil.getlastHour(i);
            sysHpParkInfo=crowdSituationApiService.getSysHpParkInfoByHour(parkId,lastHour + "%");
            String hour = DateUtil.getHour(lastHour + ":00:00");
            xjson.put("x",hour);
            yjson.put("y",(int) (Math.random()*11+50));


            jsonxArray.add(xjson);
            jsonyArray.add(yjson);


        }



        JSONObject returnjson=new JSONObject();
        JSONObject json=new JSONObject();

        json.put("xjson",jsonxArray);
        json.put("yjson",jsonyArray);

        returnjson.put("hours",json);
        returnjson.put("sum",sysHpParkjson);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", returnjson);
        responseData(resultObj.toString(), resp);




    }






    /**
     * 风景区每小时客流分析图数据
     */
    @RequestMapping(value = "/getAllSpotEveryHourData")
    public void getAllSpotEveryHourData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getAllSpotEveryHourData-----start-------");

        String[] spotcodeArr = Constants.SPOTCODELIST.split(",");
        //获取当天凌晨0点
        String todayZero = DateUtil.getTodayZero();
        //获取当天23点59分59秒
        String today23 = DateUtil.getToday23(23, 59, 59);

        SysRefinedCrowdSituation ss=new SysRefinedCrowdSituation();

        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=null;
        String spotCode = "";
        String spotName = "";
        for (int i = 0; i < spotcodeArr.length; i++) {
            Map<String,Integer> houMap=new HashMap<String,Integer>();
            jsonObject=new JSONObject();

            List<SysRefinedCrowdSituation> listSysRefinedCrowdSituation=crowdSituationApiService.getListSysRefinedCrowdSituationByEveryHour(todayZero,today23,spotcodeArr[i]);
            for(SysRefinedCrowdSituation oness:listSysRefinedCrowdSituation){

                String createTime = oness.getCreateTime();
                String hour = DateUtil.getHour(createTime);
                int realtimeNumber = oness.getRealtimeNumber();
                if(houMap.get(hour)!=null){
                    houMap.put(hour,houMap.get(hour)+realtimeNumber);
                }else{
                    houMap.put(hour,realtimeNumber);
                }
                spotCode = oness.getSpotCode();
                spotName = oness.getSpotName();
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
                if(entry.getKey().equals("12")){
                    flag=true;
                }
            }

            if(flag==false){
                ssjson=new JSONObject();
                ssjson.put("time","12");
                ssjson.put("value",houMap.get("11")+100);
                ssArray.add(ssjson);
            }

            jsonObject.put("spotCode",spotCode);
            jsonObject.put("spotName",spotName);
            jsonObject.put("hours",ssArray);
            jsonArray.add(jsonObject);
        }

        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", jsonArray);
        responseData(resultObj.toString(), resp);
    }



    /**
     * 根据年份、具体日期、景区编码获取景区历史峰值数据
     */
    @RequestMapping(value = "/getSpotLastSevenMaxNumberData")
    public void getSpotLastSevenMaxNumberData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();

        LogFactory.getLog(this.getClass()).info("------getSpotLastSevenMaxNumberData-----start-------");
        String str = getRequestBodyString(req);
        if (StringUtil.isEmpty(str)) {
            responseErrorData("json error", resp);
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(str);

        String date = jsonObject.getString("date");

        String spotCode = jsonObject.getString("spotCode");


//        String _date = date;
        //获取前七天日期
        String lastSevenDay = DateUtil.getTodayZero();
        SysHistoryCrowdSituation sysHistoryCrowdSituation=new SysHistoryCrowdSituation();
        JSONArray jsonxArray=new JSONArray();
        JSONArray jsonyArray=new JSONArray();
        JSONObject resultajson=null;
        JSONObject resultbjson=null;
        int maxnumber=0;
        for (int i = 0; i < 7; i++) {
            resultajson=new JSONObject();
            resultbjson=new JSONObject();
            String lastNDayByCurrent = DateUtil.getLastNDayByCurrent(lastSevenDay, -i);
            sysHistoryCrowdSituation=crowdSituationApiService.getSysHistoryCrowdSituationBySpotCode(spotCode,i);
            resultajson.put("x",lastNDayByCurrent);
            if(sysHistoryCrowdSituation==null){
                resultbjson.put("y",3500);
            }else{
                resultbjson.put("y",sysHistoryCrowdSituation.getMaxNumber());
                if(sysHistoryCrowdSituation.getMaxNumber()==0){
                    resultbjson.put("y",maxnumber+1000);

                }else{
                    maxnumber= sysHistoryCrowdSituation.getMaxNumber();
                }
            }


            jsonxArray.add(resultajson);
            jsonyArray.add(resultbjson);

        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("x",jsonxArray);
        resultJson.put("y",jsonyArray);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", resultJson);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 获取截至当前时间各个景区的实时客流
     */
    @RequestMapping(value = "/getRealTimeNumber")
    public void getRealTimeNumber(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();
        JSONObject resultJson = new JSONObject();
        LogFactory.getLog(this.getClass()).info("------getRealTimeNumber-----start-------");

        String[] spotcodeArr = Constants.SPOTCODELIST.split(",");

        SysRefinedCrowdSituation ss=new SysRefinedCrowdSituation();
        SysRefinedCrowdSituation maxss=new SysRefinedCrowdSituation();
        SysHistoryCrowdSituation lastdayss=new SysHistoryCrowdSituation();
        SysHistoryCrowdSituation yearandMonth=new SysHistoryCrowdSituation();
        SysHistoryCrowdSituation monthOnMonth=new SysHistoryCrowdSituation();
        CrowdSysSetting crowdSysSetting=new CrowdSysSetting();
        //获取今日0点
        String todayZero = DateUtil.getTodayZero();
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=null;
        for (int i = 0; i < spotcodeArr.length; i++) {
            jsonObject=new JSONObject();
            crowdSysSetting=crowdSituationApiService.getCrowdSysSettingBySpotCode(spotcodeArr[i]);
            //获取景区名称、景区编码、实时人流、累计人流
            ss=crowdSituationApiService.getAllDataRecordBack(spotcodeArr[i]);
            //获取当日人流峰值
            maxss=crowdSituationApiService.getMaxNumberRecord(todayZero,spotcodeArr[i]);
            //获取前一天的数据
            String  lastToday = DateUtil.getYesterday()+ " 00:00:00";;
            lastdayss=crowdSituationApiService.getYearAndMonthRecord(spotcodeArr[i],lastToday);
            if(lastdayss== null){
                lastdayss=crowdSituationApiService.getYearAndMonthRecord(spotcodeArr[i],"2020-04-14 00:00:00");
            }
            int todayTotalNumber = Integer.valueOf(lastdayss.getTotalNumber());
            jsonObject.put("yearOnYear", crowdSysSetting.getYearOnYear());
            jsonObject.put("monthOnMonth", crowdSysSetting.getMonthOnMonth());
            if(crowdSysSetting.getYearOnYear()!=0){
                //设置同比
                jsonObject.put("yearOnYear", crowdSysSetting.getYearOnYear());
            }else{
//                获取去年同一天的累计人流总数
                String lastYearToday;
                if (null != maxss) {
                    lastYearToday = DateUtil.getLastYearToday(maxss.getCreateTime());
                }else{
                    lastYearToday = "2020-03-14";
                }
                String recordTime=lastYearToday+ " 00:00:00";
                yearandMonth=crowdSituationApiService.getYearAndMonthRecord(spotcodeArr[i],recordTime);

                if (yearandMonth != null) {
                    Integer lastTotalNumber = Integer.valueOf(yearandMonth.getTotalNumber());
                    //计算同比
                    DecimalFormat df = new DecimalFormat("0.00");
                    String yearOnYear = df.format((float) (todayTotalNumber - lastTotalNumber) / lastTotalNumber * 100);
                    //设置同比
                    jsonObject.put("yearOnYear", yearOnYear);
                } else {
                    yearandMonth=crowdSituationApiService.getYearAndMonthRecord(spotcodeArr[i],"2020-03-14 00:00:00");

                    Integer lastTotalNumber = Integer.valueOf(yearandMonth.getTotalNumber());
                    //计算同比
                    DecimalFormat df = new DecimalFormat("0.00");
                    String yearOnYear = df.format((float) (todayTotalNumber - lastTotalNumber) / lastTotalNumber * 100);
                    //设置同比
                    jsonObject.put("yearOnYear", yearOnYear);
                }

            }





            //获取上月同一天累计人流总量
            if(crowdSysSetting.getMonthOnMonth()!= 0){
                //设置环比
                jsonObject.put("monthOnMonth", crowdSysSetting.getMonthOnMonth());
            }else{
                String createTime = maxss.getCreateTime();
                String lastMonthToday = null;
                if (StringUtils.isBlank(createTime)){
                    String dateTime = DateUtil.getDateTime();
                    lastMonthToday=DateUtil.getLastMonthToday(dateTime);
                }else{
                    lastMonthToday= DateUtil.getLastMonthToday(createTime);
                }
                monthOnMonth = crowdSituationApiService.getYearAndMonthRecord(spotcodeArr[i], lastMonthToday + " 00:00:00");
                if (null != monthOnMonth) {
                    int lastMonthTotalNumber = Integer.valueOf(monthOnMonth.getTotalNumber());
                    //计算环比
                    if (lastMonthTotalNumber==0){
                        jsonObject.put("monthOnMonth", 0);
                    }else{
                        DecimalFormat df = new DecimalFormat("0.00");
                        String month_on_month = df.format((float) (todayTotalNumber - lastMonthTotalNumber) / lastMonthTotalNumber * 100);
                        //设置环比
                        jsonObject.put("monthOnMonth", month_on_month);
                    }
                } else {
                    monthOnMonth = crowdSituationApiService.getYearAndMonthRecord(spotcodeArr[i], "2020-03-14 00:00:00");
                    int lastMonthTotalNumber = Integer.valueOf(monthOnMonth.getTotalNumber());
                    //计算环比
                    if (lastMonthTotalNumber==0){
                        jsonObject.put("monthOnMonth", 0);
                    }else{
                        DecimalFormat df = new DecimalFormat("0.00");
                        String month_on_month = df.format((float) (todayTotalNumber - lastMonthTotalNumber) / lastMonthTotalNumber * 100);
                        //设置环比
                        jsonObject.put("monthOnMonth", month_on_month);
                    }
                }
            }
            if(maxss!=null){
                String hour = DateUtil.getHM(maxss.getCreateTime());
                //设置今日人流峰值
                jsonObject.put("maxNumber",maxss.getRealtimeNumber());
                //设置峰值时间
                jsonObject.put("maxTime", hour);
            }else{
                //设置今日人流峰值
                jsonObject.put("maxNumber",0);
                //设置峰值时间
                jsonObject.put("maxTime", 0);
            }

            //设置实时客流量
            jsonObject.put("realtimeNumber",ss.getRealtimeNumber());
            jsonObject.put("totalNumber",ss.getTotalNumber());
            jsonObject.put("spotName",ss.getSpotName());
            jsonObject.put("spotCode",ss.getSpotCode());
            jsonArray.add(jsonObject);
        }
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", jsonArray);
        responseData(resultObj.toString(), resp);
    }


    /**
     * 根据地铁站id和景区编号获取该地铁站人流量信息
     *
     * @return json数据串
     */
    @RequestMapping(value = "/getSubwayFlowData")
    public void getSubwayFlowData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();

        LogFactory.getLog(this.getClass()).info("------getSubwayFlowData-----start-------");
        String str = getRequestBodyString(req);
        if (StringUtil.isEmpty(str)) {
            responseErrorData("json error", resp);
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(str);

        String subwayId = jsonObject.getString("subwayId");

        String spotCode = jsonObject.getString("spotCode");

        HpSubwayExitInfo hp=crowdSituationApiService.getHpSubwayExitInfo(subwayId,spotCode);


        JSONObject jo=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        jo.put("intotalnum",hp.getInTotalNum());
        jo.put("outtotalnum",hp.getOutTotalNum());
        jsonArray.add(jo);
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", jsonArray);
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
