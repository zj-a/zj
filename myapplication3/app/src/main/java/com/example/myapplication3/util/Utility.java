package com.example.myapplication3.util;

import com.example.myapplication3.db.City;
import com.example.myapplication3.db.County;
import com.example.myapplication3.db.Province;
import com.example.myapplication3.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.text.TextUtils.*;

public class Utility {
    /*解析和处理服务器返回的省级数据 */
    public static boolean handleProvinceResponse(String response){
        if (isEmpty(response)) {
            return false;//解析失败
        }  //如果返回的数据不为空
        try {
            //将所有的省级数据解析出来，并组装成实体类对像
            JSONArray allProvinces = new JSONArray(response);
            for (int i=0;i<allProvinces.length();i++){
                JSONObject provinceObject = allProvinces.getJSONObject(i);
                Province province = new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode(provinceObject.getInt("id"));
                //将该实体类对象存入数据库
                province.save();
            }
            return true;//解析成功
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;//解析失败
    }

    /*解析和处理服务器返回的市级数据 */
    public static boolean handleCityResponse(String response,int provinceId){
        if (!isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i=0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);  //所属的省级代号
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /*解析和处理服务器返回的县级数据 */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    //县级天气信息
                    county.setWeatherId(countyObject.getString("weather_id"));
                    //所属的市级代号
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /*将返回的JSON数据解析成Weather实体类*/
    public static Weather handleWeatherResponse(String response){
        try {
            //通过JSONObject和JSONArray将天气数据中的主体内容解析出来
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            //将JSON数据转换成Weather对象
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
