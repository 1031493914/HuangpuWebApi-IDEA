package com.huangpuweb.api.test;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiTest1{
//    private String host = "http://localhost:8080/HuangpuWebApi_war_exploded";
//    private String host = "http://172.82.4.51:8080";
    private String host="http://localhost:8080/springmvcdemo_war_exploded";
//private String host="http://47.94.235.130:8080";
    private HttpURLConnection conn;

    private void init(String url) throws Exception {
        if (conn != null) conn.disconnect();
        conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
    }

    public void sendOutput(String value) throws Exception {
        System.out.println("sendOutput=========================================");
        System.out.println("value:" + value);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(value);
        writer.close();
    }

    public void printlnInput() throws Exception {
        int code = conn.getResponseCode();
        InputStream in = null;
        if (code == 200) {
            in = conn.getInputStream();
        } else {
            in = conn.getErrorStream();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();
        String value = reader.readLine();
        while (value != null) {
            builder.append(value);
            value = reader.readLine();
        }
        reader.close();
        System.out.println("printlnInput=========================================");
        System.out.println("basestring:" + builder.toString().trim());
        if (code == 200) {
            value = builder.toString();
        }
        System.out.println("ResponseCode:" + code);
        System.out.println("ContentType:" + conn.getContentType());
        System.out.println("value:" + value);
        System.out.println("printlnInput OK=========================================");
    }

    public void getUserCarIdByMac(String input) throws Exception {
        init(host + "/getUserCarIdByMac");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getAllHpDianZhaoDianPaiInfo(String input) throws Exception {
        init(host + "/shiGongAnQuan/getAllHpDianZhaoDianPaiInfo");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getShiGongAnQuanBuildingList(String input) throws Exception {
        init(host + "/shiGongAnQuan/getShiGongAnQuanBuildingList");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getAllEmergencyZhaMen(String input) throws Exception {
        init(host + "/emergency/getAllEmergencyZhaMen");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getShiGongMapEventItem(String input) throws Exception {
        init(host + "/shiGongAnQuan/getShiGongMapEventItem");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getAllEmergencyFireControlStation(String input) throws Exception {
        init(host + "/emergency/getAllEmergencyFireControlStation");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getAllEmergencyEvent(String input) throws Exception {
        init(host + "/emergency/getAllEmergencyEvent");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getEmergencyEventCountByMonth(String input) throws Exception {
        init(host + "/emergency/getEmergencyEventCountByMonth");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }


    public void getEmergencyEventCountByType(String input) throws Exception {
        init(host + "/emergency/getEmergencyEventCountByType");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getMapEventItem(String input) throws Exception {
        init(host + "/emergency/getMapEventItem");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getAllSpotEveryHourData(String input) throws Exception {
        init(host + "/crowd/getAllSpotEveryHourData");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }



    public void getSpotLastSevenMaxNumberData(String input) throws Exception {
        init(host + "/crowd/getSpotLastSevenMaxNumberData");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }
    public void crowdgetRealTimeNumber(String input) throws Exception {
        init(host + "/crowd/getRealTimeNumber");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getSubwayFlowData(String input) throws Exception {
        init(host + "/crowd/getSubwayFlowData");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getParkInfoRealTimeData(String input) throws Exception {
        init(host + "/crowd/getParkInfoRealTimeData");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }


    public void getEmergencyEventCountByYear(String input) throws Exception {
        init(host + "/emergency/getEmergencyEventCountByYear");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getTopYearEventList(String input) throws Exception {
        init(host + "/emergency/getTopYearEventList");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }

    public void getDianZaoDianPaiMap(String input) throws Exception {
        init(host + "/shiGongAnQuan/getDianZaoDianPaiMap");
        sendOutput(input);
        printlnInput();
        conn.disconnect();
    }


    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();
        ApiTest1 test = new ApiTest1();
        String getUserCarIdByMac=  "{\"mac\":\"5CF8CF4B-9889-87C8-48C1-4F3C38B235DC\"}";
        String getParkInfoRealTimeData = "{\"spotCode\":\"SHHPQ_YUYUAN_WAIQUAN\",\"parkId\":\"hp31010100234\"}";
        String getSubwayFlowData = "{\"spotCode\":\"SHHPQ_WAITAN\",\"subwayId\":\"1056\"}";
        String getSpotLastSevenMaxNumberData = "{\"spotCode\":\"SHHPQ_YUYUAN_WAIQUAN\"}";
        String setKugooInfo = "{\"phonecompany\":\"phonecompany\",\"phonetype\":\"phonetype\",\"phonesystem\":\"phonesystem\",\"phoneimei\":\"phoneimei\"}";
        String setKugooBlueInfo = "{\"deviceid\":\"1\",\"mac\":\"mac\",\"companycode\":\"companycode\"}";
        String checkAppkeyAndPackage = "{\"appkey\":\"15825267071404664\",\"packagename\":\"Kugoo\"}";
//		String getAuthInfo="{\"data\":{\"url\":\"http://www.baidu.com\",\"appid\":\"ww805e7a0894cc9f84\"}}";
//		String getMatchUserList="{\"data\":{\"wxuserid\":\"wm0dEaDAAArpfoJVrVl2ZqEoRFcmE4qQ\",\"pid\":\"100000360484\"}}";
//		test.getreportList(getreportList);
        //String getIposRecord="{\"currentpage\":\"1\",\"startDate\":\"2014-3-20\",\"endDate\":\"2014-3-20\",\"iposHwNo\":\"q343\",\"sn\":\"\"}";
//		test.getUserListTest(getlogin);
//		test.getlogin(getlogin);
//		test.getUserOffligetRuiYunUserInfoneOrderList(getUserOfflineOrderList);
        test.getUserCarIdByMac(getUserCarIdByMac);
        long t = System.nanoTime() - start;
        System.out.println("调用消耗时间：" + t / 1000000000.0 + "秒");
    }
}