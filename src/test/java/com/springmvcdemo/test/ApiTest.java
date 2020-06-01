package com.springmvcdemo.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiTest {
	//http://localhost:8080/weixinapi/
//	private String host = "http://localhost:8080/weixinapi";
	private String host="http://kugooapi.zhaimob.com";
//	private String host=  "http://47.110.148.155:8082";
//	private String host="http://localhost:8080/springmvcdemo_war_exploded";
//	private String host="http://112.124.46.167:8083/threeiposapi";
	private HttpURLConnection conn;
	private void init(String url)throws Exception{
		if(conn != null) conn.disconnect();
		conn = (HttpURLConnection)new URL(url).openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");		
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
	}
	public void sendOutput(String value) throws Exception{
		System.out.println("sendOutput=========================================");
		System.out.println("value:"+value);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(value);
		writer.close();
	}
	public void printlnInput() throws Exception{
		int code = conn.getResponseCode();
		InputStream in = null;
		if(code == 200){
			in = conn.getInputStream();
		}else{
			in = conn.getErrorStream();
		}		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder builder = new StringBuilder();
		String value = reader.readLine();
		while(value != null){
			builder.append(value);
			value = reader.readLine();
		}
		reader.close();		
		System.out.println("printlnInput=========================================");
		System.out.println("basestring:"+builder.toString().trim());
		if(code == 200){
			value =builder.toString();
		}
		System.out.println("ResponseCode:"+code);
		System.out.println("ContentType:"+conn.getContentType());
		System.out.println("value:"+value);
		System.out.println("printlnInput OK=========================================");
	}	
	
	
	//setKugooInfo
	public void setKugooInfo(String input) throws Exception{
		init(host+"/setKugooInfo");
		sendOutput(input);	
		printlnInput();
		conn.disconnect();
	}
	
	
	public void setKugooBlueInfo(String input) throws Exception{
		init(host+"/setKugooBlueInfo");
		sendOutput(input);	
		printlnInput();
		conn.disconnect();
	}
	
	
	
	public void getKugooSetting(String input) throws Exception{
		init(host+"/getKugooSetting");
		sendOutput(input);	
		printlnInput();
		conn.disconnect();
	}

	
	public void setKugooLngLatInfo(String input) throws Exception{
		init(host+"/setKugooLngLatInfo");
		sendOutput(input);	
		printlnInput();
		conn.disconnect();
	}

	//getLngLatByHistoryDevice
	public void getLngLatByHistoryDevice(String input) throws Exception{
		init(host+"/getLngLatByHistoryDevice");
		sendOutput(input);
		printlnInput();
		conn.disconnect();
	}


	public static void main(String[] args) throws Exception {
		ApiTest test = new ApiTest();
		String setKugooInfo="{\"phonecompany\":\"phonecompany\",\"phonetype\":\"phonetype\",\"phonesystem\":\"phonesystem\",\"phoneimei\":\"phoneimei\"}";
		String setKugooBlueInfo="{\"deviceid\":\"1\",\"mac\":\"mac\",\"companycode\":\"companycode\"}";
		String setKugooLngLatInfo="{\"deviceid\":\"1\",\"lng\":\"lng\",\"lat\":\"lat\"}";
//		String getAuthInfo="{\"data\":{\"url\":\"http://www.baidu.com\",\"appid\":\"ww805e7a0894cc9f84\"}}";
//		String getMatchUserList="{\"data\":{\"wxuserid\":\"wm0dEaDAAArpfoJVrVl2ZqEoRFcmE4qQ\",\"pid\":\"100000360484\"}}";
//		test.getreportList(getreportList);
		//String getIposRecord="{\"currentpage\":\"1\",\"startDate\":\"2014-3-20\",\"endDate\":\"2014-3-20\",\"iposHwNo\":\"q343\",\"sn\":\"\"}";
//		test.getUserListTest(getlogin);
//		test.getlogin(getlogin);
//		test.getUserOffligetRuiYunUserInfoneOrderList(getUserOfflineOrderList);
		test.getKugooSetting("111");


//		String str="renderReverse&&renderReverse({\"status\":0,\"result\":{\"location\":{\"lng\":0.0,\"lat\":0.0},\"formatted_address\":\"\",\"business\":\"\",\"addressComponent\":{\"country\":\"\",\"country_code\":-1,\"country_code_iso\":\"\",\"country_code_iso2\":\"\",\"province\":\"\",\"city\":\"\",\"city_level\":2,\"district\":\"\",\"town\":\"\",\"town_code\":\"\",\"adcode\":\"0\",\"street\":\"\",\"street_number\":\"\",\"direction\":\"\",\"distance\":\"\"},\"pois\":[],\"roads\":[],\"poiRegions\":[],\"sematic_description\":\"\",\"cityCode\":0}}";
//		int startindex=str.indexOf("(");
//		String alladdress=str.substring(startindex+1,str.length()-1);
//		System.out.println("---------------alladdress--------------------"+alladdress);


	}
	
	
	
	
}
