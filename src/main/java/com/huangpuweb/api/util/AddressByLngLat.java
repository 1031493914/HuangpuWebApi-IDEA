package com.huangpuweb.api.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * created by Intellij IDEA
 *
 * @author Chengzhifeng
 * @date 2019/12/3
 * @statement
 */
public class AddressByLngLat {

    public static String  getAddressByLngLat(String lng,String lat){
        String address = "";
        try{

            URL url = new URL("http://api.map.baidu.com/geocoder/v2/?ak=PyLa5E6K6BEGI0euwz1s9dWc&callback=renderReverse&location="+ lat + "," + lng + "&output=json&pois=1");
            HttpURLConnection ucon = (HttpURLConnection) url.openConnection();
            ucon.connect();

            InputStream in = ucon.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            address = reader.readLine();

        }catch(Exception e){
            e.printStackTrace();
            address="";
        }


        return address;





    }



}
