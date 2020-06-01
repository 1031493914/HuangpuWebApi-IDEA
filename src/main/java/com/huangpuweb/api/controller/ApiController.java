package com.huangpuweb.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huangpuweb.api.Constants;
import com.huangpuweb.api.model.CrowdSysSetting;
import com.huangpuweb.api.model.SysHistoryCrowdSituation;
import com.huangpuweb.api.model.SysRefinedCrowdSituation;
import com.huangpuweb.api.service.ApiService;
import com.huangpuweb.api.service.CrowdSituationApiService;
import com.huangpuweb.api.util.DateUtil;
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


/**
 * 开放首页接口
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {

    private static Logger logger = Logger.getLogger(ApiController.class);

    private ApiService apiService;

    @Autowired
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }


    private CrowdSituationApiService crowdSituationApiService;

    @Autowired
    public void setCrowdSituationApiService(CrowdSituationApiService crowdSituationApiService) {
        this.crowdSituationApiService = crowdSituationApiService;
    }

    /**
     * 首页客流数据
     * @return List<Record>
     */
    @RequestMapping(value = "/flowData")
    public void flowData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        JSONObject resultObj = new JSONObject();

        LogFactory.getLog(this.getClass()).info("------flowData-----start-------");


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
            if(maxss==null){
                //设置今日人流峰值
                jsonObject.put("maxNumber",0);
                //设置峰值时间
                jsonObject.put("maxTime", 0);
            }else{
                String hour = DateUtil.getHM(maxss.getCreateTime());
                //设置今日人流峰值
                jsonObject.put("maxNumber",maxss.getRealtimeNumber());
                //设置峰值时间
                jsonObject.put("maxTime", hour);
            }

            if(ss==null){

                //设置实时客流量
                jsonObject.put("realtime_number",0);
                jsonObject.put("spot_name",0);
                jsonObject.put("record_time",0);
                jsonObject.put("level",0);
                jsonObject.put("spotCode",0);
                jsonObject.put("total_number",0);
            }else{
                String level = judgeLevel(ss.getSpotCode(), ss.getRealtimeNumber());
                //设置实时客流量
                jsonObject.put("realtime_number",ss.getRealtimeNumber());
                jsonObject.put("spot_name",ss.getSpotName());
                jsonObject.put("record_time",ss.getUpdateTime());
                jsonObject.put("level",level);
                jsonObject.put("spotCode",ss.getSpotCode());
                jsonObject.put("total_number",ss.getTotalNumber());
            }

            jsonArray.add(jsonObject);
        }
        resultObj.put("error_code", 0);
        resultObj.put("error_msg", "获取成功");
        resultObj.put("data", jsonArray);
        responseData(resultObj.toString(), resp);

    }

    /**
     * 计算风险等级
     * @param spotCode 景区编码
     * @param realtimeNumber 实时客流
     * @return String
     */
    private String judgeLevel(String spotCode, int realtimeNumber) {
        String level = null;
        switch (spotCode) {
            case "SHHPQ_WAITAN":
                if (realtimeNumber < 10000) {
                    level = "四级";
                } else if (realtimeNumber >= 10000 && realtimeNumber < 15000) {
                    level = "三级";
                } else if (realtimeNumber >= 15000 && realtimeNumber < 20000) {
                    level = "二级";
                }else{
                    level = "一级";
                }
                break;
            case "SHHPQ_NANJINGLU":
                if (realtimeNumber < 10000) {
                    level = "四级";
                } else if (realtimeNumber >= 10000 && realtimeNumber < 15000) {
                    level = "三级";
                } else if (realtimeNumber >= 15000 && realtimeNumber < 20000) {
                    level = "二级";
                }else{
                    level = "一级";
                }
                break;
            case "SHHPQ_TIANZIFANG":
                if (realtimeNumber < 2000) {
                    level = "四级";
                } else if (realtimeNumber >= 2000 && realtimeNumber < 4000) {
                    level = "三级";
                } else if (realtimeNumber >= 4000 && realtimeNumber < 5000) {
                    level = "二级";
                }else{
                    level = "一级";
                }
                break;
            case "SHHPQ_XINTIANDI":
                if (realtimeNumber < 2000) {
                    level = "四级";
                } else if (realtimeNumber >= 2000 && realtimeNumber < 4000) {
                    level = "三级";
                } else if (realtimeNumber >= 4000 && realtimeNumber < 7000) {
                    level = "二级";
                }else{
                    level = "一级";
                }
                break;
            case "SHHPQ_YUYUAN_NEIQUAN":
                if (realtimeNumber < 20000) {
                    level = "四级";
                } else if (realtimeNumber >= 20000 && realtimeNumber < 30000) {
                    level = "三级";
                } else if (realtimeNumber >= 30000 && realtimeNumber < 40000) {
                    level = "二级";
                }else{
                    level = "一级";
                }
                break;
            case "SHHPQ_YUYUAN_WAIQUAN":
                if (realtimeNumber < 20000) {
                    level = "四级";
                } else if (realtimeNumber >= 20000 && realtimeNumber < 30000) {
                    level = "三级";
                } else if (realtimeNumber >= 30000 && realtimeNumber < 40000) {
                    level = "二级";
                }else{
                    level = "一级";
                }
                break;
        }
        return level;
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
